package com.example.payment.payment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SamplePaymentController {




    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping(value = "/payment", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> welcomeCustomer(){
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<>(String.format("Welcome Customer ! I am %s and your payment has been made ", user), HttpStatus.OK);
    }


}
