package com.example.mappings.mappings.controller;

import com.example.mappings.mappings.entities.Users;
import com.example.mappings.mappings.requests.CreateUserRequest;
import com.example.mappings.mappings.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
public class UserController {


    @Autowired
    UserService userService;

    @PostMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Users> createAUser(@Valid @RequestBody CreateUserRequest userInfo){
        log.info("Request Received {} " , userInfo);
        return new ResponseEntity<>(userService.createUser(userInfo), HttpStatus.CREATED);
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
