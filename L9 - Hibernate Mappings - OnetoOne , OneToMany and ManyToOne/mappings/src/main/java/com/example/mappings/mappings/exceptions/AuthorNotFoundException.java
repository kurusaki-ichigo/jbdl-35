package com.example.mappings.mappings.exceptions;

import com.example.mappings.mappings.enums.StatusCodes;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class AuthorNotFoundException extends BaseException{
    public AuthorNotFoundException(StatusCodes statusCodes) {
        super(statusCodes);
    }
}