package com.example.mappings.mappings.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum StatusCodes {

    USER_ALREADY_EXISTS("ONBOARDING_USER_01", "User Already Exists", HttpStatus.BAD_REQUEST),
    INVALID_INPUT("INVALID_INPUT", "Invalid Input", HttpStatus.BAD_REQUEST),
    USER_DOES_NOT_EXISTS("SEARCHING_USER_01", "User Not Exists", HttpStatus.BAD_REQUEST);

    private final String message;
    private final String messageCode;
    private final HttpStatus status;
}
