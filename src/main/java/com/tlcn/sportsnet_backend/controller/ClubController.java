package com.tlcn.sportsnet_backend.controller;

import com.tlcn.sportsnet_backend.dto.club.ClubCreateRequest;
import com.tlcn.sportsnet_backend.dto.sport.SportCreateRequest;
import com.tlcn.sportsnet_backend.entity.Sport;
import com.tlcn.sportsnet_backend.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/club")
public class ClubController {
    private final ClubService clubService;

    @PostMapping
    public ResponseEntity<?> createSport(@RequestBody ClubCreateRequest request) {
        return ResponseEntity.ok(clubService.createClub(request));
    }
}
