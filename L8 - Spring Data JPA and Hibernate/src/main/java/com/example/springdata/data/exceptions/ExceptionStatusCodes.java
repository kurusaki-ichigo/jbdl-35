package com.example.springdata.data.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ExceptionStatusCodes {

    INVALID_INPUT("The user request is invalid ", HttpStatus.BAD_REQUEST),
    INVALID_USER("The user does not exists for id ", HttpStatus.BAD_REQUEST);
    ;

   private final String code;

   private final HttpStatus httpStatus;
}
