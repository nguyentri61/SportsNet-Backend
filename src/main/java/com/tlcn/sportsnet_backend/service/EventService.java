package com.tlcn.sportsnet_backend.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tlcn.sportsnet_backend.dto.event.EventCreateRequest;
import com.tlcn.sportsnet_backend.dto.event.EventResponse;
import com.tlcn.sportsnet_backend.entity.Account;
import com.tlcn.sportsnet_backend.entity.Club;
import com.tlcn.sportsnet_backend.entity.Event;
import com.tlcn.sportsnet_backend.enums.EventStatusEnum;
import com.tlcn.sportsnet_backend.repository.AccountRepository;
import com.tlcn.sportsnet_backend.repository.ClubRepository;
import com.tlcn.sportsnet_backend.repository.EventRepository;
import com.tlcn.sportsnet_backend.util.SecurityUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final AccountRepository accountRepository;
    private final ClubRepository clubRepository;
    private final FileStorageService fileStorageService;

//    @Transactional
//    public EventResponse createEvent(EventCreateRequest request) {
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Account organizer = accountRepository.findByEmail(authentication.getName())
//                .orElseThrow(() -> new RuntimeException("Organizer not found"));
//
//        Club club = null;
//        if (request.getClubId() != null) {
//            club = clubRepository.findById(request.getClubId())
//                    .orElseThrow(() -> new RuntimeException("Club not found"));
//        }
//
//        String coverFilename = request.getCoverImageFilename();
//        String imagesFilenames = null;
//        if (request.getImagesFilenames() != null && !request.getImagesFilenames().isEmpty()) {
//            imagesFilenames = String.join(",", request.getImagesFilenames());
//        }
//
//        Event event = Event.builder()
//                .title(request.getTitle())
//                .description(request.getDescription())
//                .coverImage(coverFilename)
//                .images(imagesFilenames)
//                .location(request.getLocation())
//                .startTime(request.getStartTime())
//                .endTime(request.getEndTime())
//                .capacity(request.getCapacity())
//                .fee(request.getFee())
//                .recurring(request.isRecurring())
//                .recurrenceRule(request.getRecurrenceRule())
//                .eventType(request.getEventType())
//                .eventFormat(request.getEventFormat())
//                .sportType(request.getSportType())
//                .sportRule(request.getSportRule())
//                .status(EventStatusEnum.DRAFT)
//                .club(club)
//                .organizer(organizer)
//                .build();
//
//        Event saved = eventRepository.save(event);
//        return toResponse(saved);
//    }
//

//    public List<EventResponse> findAll() {
//        List<Event> events = eventRepository.findAll();
//
//        return events.stream()
//                .map(event -> toResponse(event))
//                .collect(Collectors.toList());
//    }
}
