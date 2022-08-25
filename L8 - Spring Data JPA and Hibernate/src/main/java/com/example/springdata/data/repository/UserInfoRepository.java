package com.example.springdata.data.repository;

import com.example.springdata.data.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

//@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    /*
    TYpe of queries
     */


    // JPQL
    // native queries
    // update ones


    @Query(value = "select * from user_info where user_identifier = :userIdentifier", nativeQuery = true)
    Optional<UserInfo> findSomething(@Param("userIdentifier") String userIdentifier);

    @Query(value = "select u from UserInfo u where u.userIdentifier= :userIdentifier")
    Optional<UserInfo> findSomethingWithJPQL(@Param("userIdentifier") String userIdentifier);


    Optional<UserInfo> findByUserIdentifier(String userIdentifer);




}
