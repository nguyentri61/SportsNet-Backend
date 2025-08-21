package com.tlcn.sportsnet_backend.controller;

import com.tlcn.sportsnet_backend.dto.ApiResponse;
import com.tlcn.sportsnet_backend.dto.auth.LoginDTO;
import com.tlcn.sportsnet_backend.dto.auth.LoginResponse;
import com.tlcn.sportsnet_backend.entity.Account;
import com.tlcn.sportsnet_backend.entity.RefreshToken;
import com.tlcn.sportsnet_backend.error.UnauthorizedException;
import com.tlcn.sportsnet_backend.service.AccountService;
import com.tlcn.sportsnet_backend.service.RefreshTokenService;
import com.tlcn.sportsnet_backend.util.SecurityUtil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final SecurityUtil securityUtil;
    private final AuthenticationManager authenticationManager;
    private final AccountService accountService;
    private final RefreshTokenService refreshTokenService;
    @Value("${jwt.token-verify-validity-in-seconds}")
    private long refreshTokenExpiration;


    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginDTO loginDTO,
            @RequestHeader(value = "X-Device-Id", required = false) String deviceId,
            HttpServletRequest request
    ) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword());

        try {
            // Xác thực đăng nhập
            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            // Lấy thông tin người dùng từ DB
            Account account = accountService.findByEmail(loginDTO.getEmail())
                    .orElseThrow(() -> new UnauthorizedException("Tài khoản không tồn tại"));

            // Tạo access token
            String accessToken = securityUtil.createToken(authentication);

            if (deviceId == null || deviceId.isBlank()) {
                deviceId = UUID.randomUUID().toString();
            }

            String refreshToken = refreshTokenService.create(
                    account,
                    deviceId,
                    request.getHeader("User-Agent"),
                    request.getRemoteAddr()
            );

            ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken)
                    .httpOnly(true)
                    .secure(true)
                    .path("/")
                    .maxAge(refreshTokenExpiration)
                    .build();

            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE, cookie.toString())
                    .header("X-Device-Id", deviceId)
                    .body(ApiResponse.success(Map.of("accessToken", accessToken)));

        } catch (LockedException e) {
            throw new UnauthorizedException("Tài khoản bị khóa");
        } catch (DisabledException e) {
            throw new UnauthorizedException("Tài khoản chưa được kích hoạt");
        } catch (BadCredentialsException e) {
            throw new UnauthorizedException("Email hoặc mật khẩu không đúng");
        }
    }

    @GetMapping("/refresh")
    public ResponseEntity<?> refreshAccessToken(
            @CookieValue(value = "refreshToken", defaultValue = "") String refreshToken,
            @RequestHeader("X-Device-Id") String deviceId
    ) {
        if (refreshToken.isBlank()) {
            throw new UnauthorizedException("Bạn chưa đăng nhập hoặc thiếu refresh token");
        }

        RefreshToken token = refreshTokenService.verify(refreshToken, deviceId);
        Account account = token.getAccount();

        String newAccessToken = securityUtil.createTokenFromAccount(account);
        String newRefresh = refreshTokenService.create(
                account,
                deviceId,
                token.getUserAgent(),
                token.getIpAddress()
        );

        ResponseCookie cookie = ResponseCookie.from("refreshToken", newRefresh)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(refreshTokenExpiration)
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .header("X-Device-Id", deviceId)
                .body(ApiResponse.success(Map.of("accessToken", newAccessToken)));
    }


    @PostMapping("/logout")
    public ResponseEntity<?> logout(
            @CookieValue("refreshToken") String refreshToken,
            @RequestHeader("X-Device-Id") String deviceId
    ) {
        refreshTokenService.revoke(refreshToken, deviceId); // tìm theo token + deviceId và xóa

        ResponseCookie clearCookie = ResponseCookie.from("refreshToken", "")
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(0)
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, clearCookie.toString())
                .body(ApiResponse.success("Đăng xuất thành công", null));
    }

    @GetMapping("/verify")
    public ResponseEntity<?> verifyToken(Authentication authentication) {
        Account account = accountService.findByEmail(authentication.getName())
                .orElseThrow(() -> new UnauthorizedException("Không tìm thấy tài khoản"));

        List<String> roles = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return ResponseEntity.ok(ApiResponse.success(Map.of(
                "email", authentication.getName(),
                "roles", roles
        )));
    }
}
