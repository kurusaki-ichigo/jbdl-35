package com.example.demo.controller;

import com.example.demo.request.CreateNotificationDto;
import com.example.demo.response.ResponseGenerator;
import com.example.demo.service.AccountService;
import com.example.demo.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
public class NotificationsController {


    @Autowired
    NotificationService notificationService;

    @Autowired
    AccountService accountService;

    @Autowired
    ResponseGenerator responseGenerator;

    @PostMapping(value = "/v2/notifications", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createABook(@Valid @RequestBody CreateNotificationDto notificationDto) {
        log.info("Request Received {} ", notificationDto);
        return responseGenerator.generateResponse(notificationService.persistNotification(notificationDto), HttpStatus.CREATED);
    }



    @PostMapping(value = "/v2/accounts", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createAccount(@Valid @RequestBody CreateNotificationDto notificationDto) {
        log.info("Request Received {} ", notificationDto);
        return responseGenerator.generateResponse(accountService.persistAccounts(notificationDto), HttpStatus.CREATED);
    }

}
