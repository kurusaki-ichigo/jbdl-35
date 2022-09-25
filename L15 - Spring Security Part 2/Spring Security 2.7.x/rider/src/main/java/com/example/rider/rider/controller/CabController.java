package com.example.rider.rider.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class CabController {





    @PreAuthorize("hasRole('DRIVER')")
    @GetMapping(value = "/driver", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> welcomeMerchant(){
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return new ResponseEntity<>(String.format("Welcome Driver ! I am %s ", user), HttpStatus.OK);
    }


    @PreAuthorize("hasRole('RIDER')")
    @GetMapping(value = "/rider", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> welcomeCustomer(){
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<>(String.format("Welcome Rider ! I am %s ", user), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('RIDER') OR hasRole('DRIVER')")
    @GetMapping(value = "/rating", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> rating(){
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<>(String.format("Welcome !  %s  you are inside rating !", user), HttpStatus.OK);
    }



}
