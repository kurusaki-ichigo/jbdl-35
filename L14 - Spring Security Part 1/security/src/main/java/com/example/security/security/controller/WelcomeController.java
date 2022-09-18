package com.example.security.security.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class WelcomeController {

    /**
     * would I be able to access the api
     *     1 -- welcome ?
     *   Yes / No
     *                  (a)
     *
     *     2 -- merchant ?
     *   Yes / No
     *                  (a)
     *
     *     3 -- customer ?
     *   Yes / No
     *                  (a)
     *
     *   -- If yes
     *          -- Directly or Indirectly
     *               (a)        (b)
     *    -- No
     *      (c)
     *
     *
     *      The answer is (b)
     *
     *
     *
     *  FE ----------> [ (     ) Spring boot app (embedded tomcat)] ---------> Database
     *
     *                  ( | \   \   \ (Authentication) \   \  (UserNamePasswordAuthn) \   \   \    (Authorisation)   \   \   \     )
     *
     *
     *
     *
     *
     *  MICROSERVICES
     *
     *
     *      USER MICROSERVICE (different deployed -- deployed at different servicer)
     *
     *
     *
     *
     *      (PAYMENT MICROSERVICE) --- make a call for a user wheneveer payment method is invoked
     *      ----->               User
     *      (userRoles) -----> towards the different controllers
     *
     *
     *
     * @return
     */
    @Secured("ROLE_BAKER")
    @GetMapping(value = "/welcome", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> welcomeUser(){
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return new ResponseEntity<>(String.format("Welcome ! I am %s ", user), HttpStatus.OK);
    }


    @PreAuthorize("hasRole('MERCHANT')")
    @GetMapping(value = "/merchant", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> welcomeMerchant(){
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return new ResponseEntity<>(String.format("Welcome Merchant ! I am %s ", user), HttpStatus.OK);
    }


    @PreAuthorize("hasRole('CUSTOMER') OR hasRole('BAKER')")
    @GetMapping(value = "/customer", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> welcomeCustomer(){
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<>(String.format("Welcome Customer ! I am %s ", user), HttpStatus.OK);
    }




}
