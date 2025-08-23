package com.tlcn.sportsnet_backend.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tlcn.sportsnet_backend.dto.sport.SportCreateRequest;
import com.tlcn.sportsnet_backend.dto.sport.SportResponse;
import com.tlcn.sportsnet_backend.entity.Sport;
import com.tlcn.sportsnet_backend.repository.AccountRepository;
import com.tlcn.sportsnet_backend.repository.ClubRepository;
import com.tlcn.sportsnet_backend.repository.SportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SportService {
    private final SportRepository sportRepository;
    private final ClubRepository clubRepository;
    private final AccountRepository accountRepository;
    private final ObjectMapper objectMapper;

    public SportResponse createSport(SportCreateRequest request) {

        try {
            // Convert Map -> JSON string
            String rulesJson = objectMapper.writeValueAsString(request.getDefaultRules());

            Sport sport = Sport.builder()
                    .name(request.getName())
                    .type(request.getType())
                    .defaultRules(rulesJson)  // ✅ gán JSON string vào entity
                    .build();

            Sport saved = sportRepository.save(sport);

            Map<String, Object> rulesMap =
                    objectMapper.readValue(saved.getDefaultRules(), Map.class);

            return SportResponse.builder()
                    .id(saved.getId())
                    .name(saved.getName())
                    .type(saved.getType())
                    // parse JSON string lại thành Map khi trả response
                    .defaultRules(rulesMap)
                    .build();

        } catch (Exception e) {
            throw new RuntimeException("Error processing sport rules JSON", e);
        }
    }

    public List<SportResponse> getAllSport() {
        List<Sport> sports = sportRepository.findAll();
        return sports.stream()
                .map(s -> {
                    Map<String, Object> rules = null;
                    try {
                        if (s.getDefaultRules() != null) {
                            rules = objectMapper.readValue(s.getDefaultRules(), new TypeReference<Map<String, Object>>() {});
                        }
                    } catch (Exception e) {
                        throw new RuntimeException("Error parsing rules for sport " + s.getName(), e);
                    }

                    return SportResponse.builder()
                            .id(s.getId())
                            .name(s.getName())
                            .type(s.getType())
                            .defaultRules(rules)
                            .build();
                })
                .collect(Collectors.toList());
    }
}
