package com.example.security.security.controller;

import com.example.security.security.entities.UserInfo;
import com.example.security.security.request.CreateUserRequestDto;
import com.example.security.security.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/user" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserInfo> createANewUser(@RequestBody CreateUserRequestDto createUserRequestDto){
        log.info(" request received {} ", createUserRequestDto);
        return new ResponseEntity<>(userService.persist(createUserRequestDto), HttpStatus.CREATED);
    }


    /**
     * ONly allow access to payment microservice but not other
     * (limit the api usage to per microservice basis by JWT authentication)
     * @param email
     * @return
     */
    @GetMapping(value = "/user/{email}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDetails> fetchAUserByEmail(@PathVariable String email){
        log.info(" recevied request for {} ", email);
        return new ResponseEntity<>(userService.loadUserByUsername(email), HttpStatus.OK);
    }





}
