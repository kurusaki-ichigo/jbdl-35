package com.example.mappings.mappings.exceptions;

import com.example.mappings.mappings.enums.StatusCodes;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookNotFoundException extends BaseException{

    public BookNotFoundException(StatusCodes statusCodes){
        super(statusCodes);
    }
}
