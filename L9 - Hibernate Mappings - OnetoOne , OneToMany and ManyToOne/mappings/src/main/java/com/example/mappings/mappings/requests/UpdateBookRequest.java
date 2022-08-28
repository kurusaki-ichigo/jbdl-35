package com.example.mappings.mappings.requests;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateBookRequest {
    @NotNull
    Long id ;
    String name;
}
