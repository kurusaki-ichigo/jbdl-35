package com.example.springdata.data.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class InvalidInputException extends RuntimeException{

    ExceptionStatusCodes statusCodes;

    public InvalidInputException(ExceptionStatusCodes exceptionStatusCodes){
        super(exceptionStatusCodes.getCode());
        this.statusCodes = exceptionStatusCodes;
    }

}
