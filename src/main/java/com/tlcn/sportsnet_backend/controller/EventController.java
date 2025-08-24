package com.tlcn.sportsnet_backend.controller;

import com.tlcn.sportsnet_backend.dto.ApiResponse;
import com.tlcn.sportsnet_backend.dto.event.EventCreateRequest;
import com.tlcn.sportsnet_backend.dto.event.EventResponse;
import com.tlcn.sportsnet_backend.entity.Event;
import com.tlcn.sportsnet_backend.service.EventService;
import com.tlcn.sportsnet_backend.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/event")
public class EventController {
    private final EventService eventService;
    private final FileStorageService fileStorageService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<EventResponse> eventResponseList = eventService.findAll();
        return ResponseEntity.ok(eventResponseList);
    }

    @PostMapping
    public ResponseEntity<?> createEvent(@RequestBody EventCreateRequest request) {
        EventResponse res = eventService.createEvent(request);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) return ResponseEntity.badRequest().body("File is empty");
        String filename = fileStorageService.storeFile(file, "/events");
        return ResponseEntity.ok()
                .body(ApiResponse.success(Map.of("fileName", filename)));
    }
}
