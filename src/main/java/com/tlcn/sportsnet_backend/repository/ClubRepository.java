package com.tlcn.sportsnet_backend.repository;

import com.tlcn.sportsnet_backend.entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubRepository extends JpaRepository<Club, String> {

}
