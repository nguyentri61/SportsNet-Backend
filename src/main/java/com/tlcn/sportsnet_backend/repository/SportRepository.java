package com.tlcn.sportsnet_backend.repository;

import com.tlcn.sportsnet_backend.entity.Sport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SportRepository extends JpaRepository<Sport, String> {

}
