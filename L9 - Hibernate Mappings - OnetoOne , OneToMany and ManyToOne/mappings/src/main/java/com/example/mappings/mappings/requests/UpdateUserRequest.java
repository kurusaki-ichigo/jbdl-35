package com.example.mappings.mappings.requests;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UpdateUserRequest {
    @NotNull
    String name;

    @NotBlank
    String email;

}
