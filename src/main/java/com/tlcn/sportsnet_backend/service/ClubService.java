package com.tlcn.sportsnet_backend.service;

import com.tlcn.sportsnet_backend.dto.club.ClubCreateRequest;
import com.tlcn.sportsnet_backend.entity.Account;
import com.tlcn.sportsnet_backend.entity.Club;
import com.tlcn.sportsnet_backend.entity.Role;
import com.tlcn.sportsnet_backend.repository.AccountRepository;
import com.tlcn.sportsnet_backend.repository.ClubRepository;
import com.tlcn.sportsnet_backend.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClubService {
    private final ClubRepository clubRepository;
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;

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

    public void activateClub(String id) {
        Club club = clubRepository.findById(id).orElseThrow(() -> new RuntimeException("Club not found"));
        club.setActive(true);

        Account account = club.getOwner();
        Role ownerRole = roleRepository.findByName("OWNER_CLUB")
                .orElseThrow(() -> new RuntimeException("Role OWNER_CLUB not found"));

        account.getRoles().add(ownerRole);

        clubRepository.save(club);
        accountRepository.save(account);
    }
}
