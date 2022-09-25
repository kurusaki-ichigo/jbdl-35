package com.example.demo.request;

import com.example.demo.entities.UserInfo;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class CreateUserRequestDto {

    private String email;
    private String password;

    private Set<String> userRoles;

    public UserInfo toUserInfo(){
        return UserInfo.builder()
                .identifier(UUID.randomUUID().toString())
                .email(email)
                .password(password)
                .userRoles(userRoles)
                .passwordRaw(password)
                .build();
    }

}
