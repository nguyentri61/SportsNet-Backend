package com.tlcn.sportsnet_backend.service;

import com.tlcn.sportsnet_backend.dto.sport.SportCreateRequest;
import com.tlcn.sportsnet_backend.dto.sport.SportResponse;
import com.tlcn.sportsnet_backend.entity.Sport;
import com.tlcn.sportsnet_backend.repository.AccountRepository;
import com.tlcn.sportsnet_backend.repository.ClubRepository;
import com.tlcn.sportsnet_backend.repository.SportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SportService {
    private final SportRepository sportRepository;
    private final ClubRepository clubRepository;
    private final AccountRepository accountRepository;

    public SportResponse createSport(SportCreateRequest request) {
        Sport sport = Sport.builder()
                .name(request.getName())
                .type(request.getType())
                .defaultRules(request.getDefaultRules())
                .build();

        Sport saved = sportRepository.save(sport);

        return SportResponse.builder()
                .id(saved.getId())
                .name(saved.getName())
                .type(saved.getType())
                .defaultRules(saved.getDefaultRules())
                .build();
    }

    public List<SportResponse> getAllSport() {
        List<Sport> sports = sportRepository.findAll();
        return sports.stream()
                .map(s -> SportResponse.builder()
                        .id(s.getId())
                        .name(s.getName())
                        .type(s.getType())
                        .defaultRules(s.getDefaultRules())
                        .build())
                .collect(Collectors.toList());
    }
}
