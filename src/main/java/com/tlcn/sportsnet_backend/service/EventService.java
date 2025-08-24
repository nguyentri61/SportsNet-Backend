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

    @Transactional
    public EventResponse createEvent(EventCreateRequest request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Account organizer = accountRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("Organizer not found"));

        Club club = null;
        if (request.getClubId() != null) {
            club = clubRepository.findById(request.getClubId())
                    .orElseThrow(() -> new RuntimeException("Club not found"));
        }

        // Xử lý upload cover image
        String coverFilename = null;
        if (request.getCoverImage() != null && !request.getCoverImage().isEmpty()) {
            coverFilename = fileStorageService.storeFile(request.getCoverImage(), "events/cover");
        }

        // Xử lý upload nhiều ảnh
        StringBuilder imagesFilenames = new StringBuilder();
        if (request.getImages() != null) {
            for (MultipartFile file : request.getImages()) {
                if (!file.isEmpty()) {
                    String filename = fileStorageService.storeFile(file, "events/images");
                    if (imagesFilenames.length() > 0) imagesFilenames.append(",");
                    imagesFilenames.append(filename);
                }
            }
        }

        Event event = Event.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .coverImage(coverFilename)
                .images(imagesFilenames.toString())
                .location(request.getLocation())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .capacity(request.getCapacity())
                .fee(request.getFee())
                .recurring(request.isRecurring())
                .recurrenceRule(request.getRecurrenceRule())
                .eventType(request.getEventType())
                .eventFormat(request.getEventFormat())
                .sportType(request.getSportType())
                .sportRule(request.getSportRule())
                .status(EventStatusEnum.DRAFT) // default khi tạo
                .club(club)
                .organizer(organizer)
                .build();

        Event saved = eventRepository.save(event);

        return toResponse(saved);
    }

    private EventResponse toResponse(Event event) {
        return EventResponse.builder()
                .id(event.getId())
                .title(event.getTitle())
                .description(event.getDescription())
                .coverImageUrl(fileStorageService.getFileUrl(event.getCoverImage(), "events/cover"))
                .images(
                        event.getImages() == null ? null :
                                String.valueOf(Arrays.stream(event.getImages().split(","))
                                        .map(img -> fileStorageService.getFileUrl(img, "events/images"))
                                        .toList())
                )
                .location(event.getLocation())
                .startTime(event.getStartTime())
                .endTime(event.getEndTime())
                .capacity(event.getCapacity())
                .fee(event.getFee())
                .recurring(event.isRecurring())
                .recurrenceRule(event.getRecurrenceRule())
                .eventType(event.getEventType())
                .eventFormat(event.getEventFormat())
                .sportType(event.getSportType())
                .sportRule(event.getSportRule())
                .status(event.getStatus())
                .clubId(event.getClub() != null ? event.getClub().getId() : null)
                .organizerId(event.getOrganizer().getId())
                .createdAt(event.getCreatedAt())
                .build();
    }

    public List<EventResponse> findAll() {
        List<Event> events = eventRepository.findAll();

        return events.stream()
                .map(event -> toResponse(event))
                .collect(Collectors.toList());
    }
}
