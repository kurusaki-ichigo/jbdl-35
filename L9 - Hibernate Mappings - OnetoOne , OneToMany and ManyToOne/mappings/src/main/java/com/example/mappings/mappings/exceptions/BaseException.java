package com.example.mappings.mappings.exceptions;

import com.example.mappings.mappings.enums.StatusCodes;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BaseException extends RuntimeException{

    private StatusCodes statusCodes;

    public BaseException(StatusCodes statusCodes){
        super(statusCodes.getMessage());
        this.statusCodes = statusCodes;
    }


}
