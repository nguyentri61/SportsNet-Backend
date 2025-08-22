package com.tlcn.sportsnet_backend.service;

import com.tlcn.sportsnet_backend.dto.event.EventCreateRequest;
import com.tlcn.sportsnet_backend.dto.event.EventResponse;
import com.tlcn.sportsnet_backend.entity.Account;
import com.tlcn.sportsnet_backend.entity.Club;
import com.tlcn.sportsnet_backend.entity.Event;
import com.tlcn.sportsnet_backend.entity.Sport;
import com.tlcn.sportsnet_backend.enums.EventStatusEnum;
import com.tlcn.sportsnet_backend.repository.AccountRepository;
import com.tlcn.sportsnet_backend.repository.ClubRepository;
import com.tlcn.sportsnet_backend.repository.EventRepository;
import com.tlcn.sportsnet_backend.repository.SportRepository;
import com.tlcn.sportsnet_backend.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final AccountRepository accountRepository;
    private final ClubRepository clubRepository;
    private final SportRepository sportRepository;

    public EventResponse createEvent(EventCreateRequest req) {
        // Lấy sport
        Sport sport = sportRepository.findById(req.getSportId())
                .orElseThrow(() -> new RuntimeException("Sport not found"));

        // Lấy organizer (current user)
        String currentUserEmail = SecurityUtil.getCurrentUserLogin()
                .orElseThrow(() -> new RuntimeException("Unauthorized"));

        Account organizer = accountRepository.findByEmail(currentUserEmail)
                .orElseThrow(() -> new RuntimeException("Organizer not found"));

        // Nếu có club
        Club club = null;
        if (req.getClubId() != null) {
            club = clubRepository.findById(req.getClubId())
                    .orElseThrow(() -> new RuntimeException("Club not found"));
        }

        Event event = Event.builder()
                .title(req.getTitle())
                .description(req.getDescription())
                .coverImageUrl(req.getCoverImageUrl())
                .location(req.getLocation())
                .startTime(req.getStartTime())
                .endTime(req.getEndTime())
                .capacity(req.getCapacity())
                .fee(req.getFee())
                .recurring(req.isRecurring())
                .recurrenceRule(req.getRecurrenceRule())
                .type(req.getType())
                .status(EventStatusEnum.DRAFT) // mặc định ban đầu
                .sport(sport)
                .club(club)
                .organizer(organizer)
                .build();

        event = eventRepository.save(event);
        return toResponse(event);
    }

    public static EventResponse toResponse(Event event) {
        return EventResponse.builder()
                .id(event.getId())
                .title(event.getTitle())
                .description(event.getDescription())
                .coverImageUrl(event.getCoverImageUrl())
                .location(event.getLocation())
                .startTime(event.getStartTime())
                .endTime(event.getEndTime())
                .capacity(event.getCapacity())
                .fee(event.getFee())
                .recurring(event.isRecurring())
                .recurrenceRule(event.getRecurrenceRule())
                .type(event.getType())
                .status(event.getStatus())
                .sportName(event.getSport() != null ? event.getSport().getName() : null)
                .clubName(event.getClub() != null ? event.getClub().getName() : null)
                .organizerName(
                        event.getOrganizer() != null && event.getOrganizer().getUserInfo() != null
                                ? event.getOrganizer().getUserInfo().getFullName()
                                : null)
                .createdAt(event.getCreatedAt())
                .updatedAt(event.getUpdatedAt())
                .build();
    }

    public List<EventResponse> findAll() {
        List<Event> events = eventRepository.findAll();

        return events.stream()
                .map(event -> toResponse(event))
                .collect(Collectors.toList());
    }
}
