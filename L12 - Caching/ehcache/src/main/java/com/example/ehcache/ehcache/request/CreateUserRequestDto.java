package com.example.ehcache.ehcache.request;

import com.example.ehcache.ehcache.entity.UserInfo;
import lombok.Data;

@Data
public class CreateUserRequestDto {

    String name;
    String email;


    public UserInfo toUserInfo(){
        return UserInfo.builder()
                .name(name).email(email).build();
    }
}
