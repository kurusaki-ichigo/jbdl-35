package com.example.user.user.service;

import com.example.user.user.entity.UserInfo;
import com.example.user.user.repository.UserInfoRepository;
import com.example.user.user.request.CreateUserRequestDto;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    UserInfoRepository userInfoRepository;



    @SneakyThrows
    public UserInfo createOrFetchExisting(CreateUserRequestDto userRequestDto){
        Thread.sleep(10000);
        UserInfo userInfo = userRequestDto.toUserInfo();
        Optional<UserInfo> byEmail = userInfoRepository.findByEmail(userInfo.getEmail());
        return byEmail.orElseGet(() -> saveOrUpdate(userInfo));
    }

    private UserInfo saveOrUpdate(UserInfo userInfo){
        return userInfoRepository.save(userInfo);
    }

}
