package com.example.mappings.mappings.requests;

import com.example.mappings.mappings.entities.Users;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Data
public class CreateUserRequest {

    @NotNull
    String name;
    @NotNull
    String email;

    public Users toUser(){
        return Users.builder()
                .name(name)
                .email(email)
                .build();
    }

}
