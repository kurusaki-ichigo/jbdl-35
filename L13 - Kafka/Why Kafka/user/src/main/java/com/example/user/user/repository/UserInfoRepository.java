package com.example.user.user.repository;

import com.example.user.user.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo , Long> {

    Optional<UserInfo> findByIdentifier(String identifier);


    Optional<UserInfo> findByEmail(String emil);



}
