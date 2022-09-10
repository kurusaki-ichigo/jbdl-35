package com.example.ehcache.ehcache.controller;

import com.example.ehcache.ehcache.entity.UserInfo;
import com.example.ehcache.ehcache.request.CreateUserRequestDto;
import com.example.ehcache.ehcache.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Slf4j
public class OnboardingController {


    @Autowired
    UserService userService;

    @PostMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserInfo> createAUser( @RequestBody CreateUserRequestDto userInfo){
        log.info("Request Received {} " , userInfo);
        return new ResponseEntity<>(userService.createUser(userInfo), HttpStatus.CREATED);
    }

    @GetMapping(value = "/user/{emailId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<UserInfo>> fetchUserByEmail(@PathVariable String emailId){
        log.info("Request Received for fetching user by {}  ", emailId);
        return new ResponseEntity<>(userService.fetchUserByEmail(emailId), HttpStatus.OK);
    }

}
