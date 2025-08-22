package com.tlcn.sportsnet_backend.repository;

import com.tlcn.sportsnet_backend.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {

}
