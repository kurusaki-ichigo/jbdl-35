package com.example.mappings.mappings.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum StatusCodes {

    USER_ALREADY_EXISTS( "User Already Exists", "ERR_ON_BOARDING_USER_01",  HttpStatus.BAD_REQUEST),
    INVALID_INPUT( "Invalid Input", "ERR_INVALID_INPUT", HttpStatus.BAD_REQUEST),
    AUTHOR_NOT_FOUND("Author Not Found", "ERR_AUTHOR_NOT_FOUND", HttpStatus.BAD_REQUEST),
    USER_DOES_NOT_EXISTS("User Not Exists", "ERR_SEARCHING_USER_01", HttpStatus.BAD_REQUEST);

    private final String message;
    private final String messageCode;
    private final HttpStatus status;
}
