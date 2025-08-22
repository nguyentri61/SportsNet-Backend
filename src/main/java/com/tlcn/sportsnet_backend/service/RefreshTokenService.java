package com.tlcn.sportsnet_backend.service;

import com.tlcn.sportsnet_backend.entity.Account;
import com.tlcn.sportsnet_backend.entity.RefreshToken;
import com.tlcn.sportsnet_backend.error.UnauthorizedException;
import com.tlcn.sportsnet_backend.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${jwt.token-verify-validity-in-seconds}")
    private Long refreshTokenExpiration;

    /**
     * Tạo refresh token cho tài khoản trên 1 thiết bị cụ thể
     */
    public String create(Account account, String deviceId, String userAgent, String ipAddress) {
        RefreshToken refreshToken = RefreshToken.builder()
                .token(UUID.randomUUID().toString())
                .deviceId(deviceId)
                .account(account)
                .userAgent(userAgent)
                .ipAddress(ipAddress)
                .expiryDate(Instant.now().plusSeconds(refreshTokenExpiration))
                .build();
       refreshTokenRepository.save(refreshToken);
       return refreshToken.getToken();
    }

    /**
     * Kiểm tra token có tồn tại và còn hạn hay không
     */
    public RefreshToken verify(String token, String deviceId) {
        return refreshTokenRepository.findByTokenAndDeviceId(token, deviceId)
                .map(rt -> {
                    if (rt.getExpiryDate().isBefore(Instant.now())) {
                        refreshTokenRepository.delete(rt);
                        throw new RuntimeException("Refresh token đã hết hạn.");
                    }
                    return rt;
                }).orElseThrow(() -> new RuntimeException("Refresh token không hợp lệ."));
    }

    /**
     * Xoá token trên thiết bị cụ thể
     */
    @Transactional
    public void revoke(String refreshToken, String deviceId) {
        RefreshToken token = refreshTokenRepository.findByTokenAndDeviceId(refreshToken, deviceId)
                .orElseThrow(() -> new UnauthorizedException("Token không tồn tại hoặc đã bị thu hồi"));

        refreshTokenRepository.delete(token);
    }
    /**
     * Xoá tất cả refresh token của user (logout toàn bộ)
     */
    public void revokeAll(Account account) {
        refreshTokenRepository.deleteAllByAccount(account);
    }

    /**
     * Lấy danh sách token đang hoạt động của người dùng
     */
    public Optional<RefreshToken> getByTokenAndDevice(String token, String deviceId) {
        return refreshTokenRepository.findByTokenAndDeviceId(token, deviceId);
    }
}
