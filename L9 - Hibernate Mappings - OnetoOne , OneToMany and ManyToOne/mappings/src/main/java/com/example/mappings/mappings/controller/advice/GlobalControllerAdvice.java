package com.example.mappings.mappings.controller.advice;

import com.example.mappings.mappings.exceptions.BaseException;
import com.example.mappings.mappings.exceptions.UserExistsException;
import com.example.mappings.mappings.utils.ResponseGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GlobalControllerAdvice {

    @Autowired
    ResponseGenerator responseGenerator;

    @ExceptionHandler(value = {UserExistsException.class})
    public ResponseEntity<String> generateExceptionResponse(BaseException baseException){
        return responseGenerator.generateResponse(baseException.getStatusCodes());
    }



}
