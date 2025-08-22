package com.tlcn.sportsnet_backend.controller;

import com.tlcn.sportsnet_backend.dto.sport.SportCreateRequest;
import com.tlcn.sportsnet_backend.entity.Sport;
import com.tlcn.sportsnet_backend.service.SportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sport")
public class SportController {
    private final SportService sportService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(sportService.getAllSport());
    }

    @PostMapping
    public ResponseEntity<?> createSport(@RequestBody SportCreateRequest request) {
        return ResponseEntity.ok(sportService.createSport(request));
    }


}
