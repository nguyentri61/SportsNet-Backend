package com.tlcn.sportsnet_backend.service;

import com.tlcn.sportsnet_backend.dto.club.ClubCreateRequest;
import com.tlcn.sportsnet_backend.entity.Account;
import com.tlcn.sportsnet_backend.entity.Club;
import com.tlcn.sportsnet_backend.repository.AccountRepository;
import com.tlcn.sportsnet_backend.repository.ClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClubService {
    private final ClubRepository clubRepository;
    private final AccountRepository accountRepository;

    public Club createClub(ClubCreateRequest request) {
        Account owner = accountRepository.findById(request.getOwnerId())
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        Club club = Club.builder()
                .name(request.getName())
                .description(request.getDescription())
                .logoUrl(request.getLogoUrl())
                .owner(owner)
                .build();
        return clubRepository.save(club);
    }
}
