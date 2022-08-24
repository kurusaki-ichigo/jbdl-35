package com.example.springdata.data.controller.advice;

import com.example.springdata.data.exceptions.InvalidInputException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalControllerAdvice {

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<String> handleInvalidInputExceptionResponse(InvalidInputException exception){
        log.info(" exception received {} ", exception.getStatusCodes());
        return new ResponseEntity<>(exception.getStatusCodes().getCode(), exception.getStatusCodes().getHttpStatus());
    }





}
