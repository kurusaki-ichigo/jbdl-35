package com.major.ewallet.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.major.ewallet.user.entity.UserInfo;
import com.major.ewallet.user.request.CreateUserRequestDto;
import com.major.ewallet.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
public class OnboardingController {


    @Autowired
    UserService userService;

    @Autowired
    ObjectMapper objectMapper;


    @PostMapping(value = "/user" , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createUser(@Valid @RequestBody CreateUserRequestDto requestDto) throws JsonProcessingException {
        UserInfo newUser = userService.createANewUser(requestDto);
        userService.sendMessage(newUser);
        return new ResponseEntity<>(objectMapper.writeValueAsString(newUser), HttpStatus.CREATED);
    }

    /**
     *         ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:9095/user/" + id, String.class);
     * @param requestDto
     * @return
     * @throws JsonProcessingException
     */

    @GetMapping(value = "/user/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getUser(@PathVariable(value = "id") Long id) throws JsonProcessingException {
        UserInfo newUser = userService.fetchUserById(id);
        return new ResponseEntity<>(objectMapper.writeValueAsString(newUser), HttpStatus.CREATED);
    }


}
