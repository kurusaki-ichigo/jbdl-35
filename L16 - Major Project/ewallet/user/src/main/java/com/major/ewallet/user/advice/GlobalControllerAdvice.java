package com.major.ewallet.user.advice;

import com.major.ewallet.user.exceptions.DuplicateUserException;
import com.major.ewallet.user.exceptions.NoUserFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(value = {DuplicateUserException.class, NoUserFoundException.class})
    public ResponseEntity<String> generateUserNotFoundResponse(){
        return new ResponseEntity<>("user already exists", HttpStatus.BAD_REQUEST);
    }




}
