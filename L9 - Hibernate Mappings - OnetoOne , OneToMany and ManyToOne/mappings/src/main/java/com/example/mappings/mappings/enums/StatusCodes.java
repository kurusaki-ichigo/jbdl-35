package com.example.mappings.mappings.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum StatusCodes {

    USER_ALREADY_EXISTS("ONBOARDING_USER_01", "User Already Exists", HttpStatus.BAD_REQUEST);

    private final String message;
    private final String messageCode;
    private final HttpStatus status;
}
