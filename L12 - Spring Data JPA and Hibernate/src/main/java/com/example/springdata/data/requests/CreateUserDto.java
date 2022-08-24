package com.example.springdata.data.requests;

import com.example.springdata.data.entity.UserInfo;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
public class CreateUserDto {

    @NotBlank
    String name;




    public UserInfo toUserInfo(){
        return UserInfo.builder()
                .userIdentifier(UUID.randomUUID())
                .name(name).build();
    }



}
