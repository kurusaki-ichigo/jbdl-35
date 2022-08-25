package com.example.springdata.data.requests;

import com.example.springdata.data.entity.UserInfo;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UpdateUserDto {

    @NotBlank
    String uuid;
    @NotBlank
    String name;



}
