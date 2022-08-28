package com.example.mappings.mappings.controller;

import com.example.mappings.mappings.requests.CreateUserRequest;
import com.example.mappings.mappings.requests.UpdateUserRequest;
import com.example.mappings.mappings.service.UserService;
import com.example.mappings.mappings.utils.ResponseGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
public class UserController {


    @Autowired
    UserService userService;

    @Autowired
    ResponseGenerator responseGenerator;

    private static final String USER_DELETED = "The given user is deleted";

    @PostMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createAUser(@Valid @RequestBody CreateUserRequest userInfo) {
        log.info("Create User Request Received {} ", userInfo);
        return responseGenerator.generateResponse(userService.createUser(userInfo), HttpStatus.CREATED);
    }

    @GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAUser(@RequestParam(value = "email") String email) {
        log.info("Get User Request Received {} ", email);
        return responseGenerator.generateResponse(userService.getUser(email), HttpStatus.OK);
    }

    @PutMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateAUser(@Valid @RequestBody UpdateUserRequest userInfo) {
        log.info("Update User Request Received {} ", userInfo);
        return responseGenerator.generateResponse(userService.updateUser(userInfo), HttpStatus.OK);
    }

    @DeleteMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteAUser(@RequestParam(value = "email") String email) {
        log.info("Get User Request Received {} ", email);
        userService.deleteUser(email);
        return responseGenerator.generateResponse(USER_DELETED, HttpStatus.OK);
    }


    /**
     *
     * User CRUD
     * C
     *
     * R U and DELETE
     *              -> AbhishesK Read, Delete
     *              -> Indibar Update
     *              -->
     *
     *  Order - ill cover tomorrow
     *
     *
     *  Book
     *      - CRUD -- Koshika
     *
     *      You cannot have a book without author - either by email (unique)
     *
     *      Book --> if author exists --> then use same author
     *      else create new authro and create book.
     *
     *      Delete a book
     *          -- should only delete book but not author
     *                      unless there does not exists any reference of that author.
     *
     *          -- Read (fetch)
     *
     *          -- Update book name / isbn
     *
     *
     */


}
