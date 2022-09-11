package com.example.user.user.request;

import com.example.user.user.entity.FoodPreference;
import com.example.user.user.entity.UserInfo;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateUserRequestDto {

    String name;
    String email;
    String foodPreference;


    public UserInfo toUserInfo(){
        boolean isVeg = FoodPreference.VEG.name().equalsIgnoreCase(foodPreference);
        return UserInfo.builder()
                .identifier(UUID.randomUUID().toString())
                .name(name)
                .email(email)
                .foodPreference(isVeg ? FoodPreference.VEG : FoodPreference.NON_VEG)
                .build();
    }
}
