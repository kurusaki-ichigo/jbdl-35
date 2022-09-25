package com.major.ewallet.user.request;

import com.major.ewallet.user.entity.UserInfo;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class CreateUserRequestDto {

    @Email
    private String email;
    @NotBlank
    private String name;
    @NotBlank
    private String contactNumber;
    private String dob;

    public UserInfo toUserInfo(){
        return UserInfo.builder()
                .email(email)
                .name(name)
                .contactNumber(contactNumber)
                .dob(dob)
                .build();
    }





}
