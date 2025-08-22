package com.tlcn.sportsnet_backend.controller;

import com.tlcn.sportsnet_backend.dto.event.EventCreateRequest;
import com.tlcn.sportsnet_backend.dto.event.EventResponse;
import com.tlcn.sportsnet_backend.entity.Event;
import com.tlcn.sportsnet_backend.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/event")
public class EventController {
    private final EventService eventService;

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
}
