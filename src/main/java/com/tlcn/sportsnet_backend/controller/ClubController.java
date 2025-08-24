package com.tlcn.sportsnet_backend.controller;

import com.tlcn.sportsnet_backend.dto.club.ClubCreateRequest;
import com.tlcn.sportsnet_backend.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/club")
public class ClubController {
    private final ClubService clubService;

    @PostMapping
    public ResponseEntity<?> createClub(@RequestBody ClubCreateRequest request) {
        return ResponseEntity.ok(clubService.createClub(request));
    }

    @PutMapping("/active/{id}")
    public ResponseEntity<?> activateClub(@PathVariable String id) {
        clubService.activateClub(id);
        return ResponseEntity.noContent().build();
    }
}
