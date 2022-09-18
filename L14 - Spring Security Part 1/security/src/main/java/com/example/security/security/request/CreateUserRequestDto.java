package com.example.security.security.request;

import com.example.security.security.entities.UserInfo;
import lombok.Data;
import lombok.ToString;

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
