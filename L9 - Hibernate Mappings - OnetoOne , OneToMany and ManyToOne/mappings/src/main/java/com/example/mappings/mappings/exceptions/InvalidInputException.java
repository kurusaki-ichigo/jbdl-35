package com.example.mappings.mappings.exceptions;

import com.example.mappings.mappings.enums.StatusCodes;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class InvalidInputException extends BaseException {
    public InvalidInputException(StatusCodes statusCodes){
        super(statusCodes);
    }
}
