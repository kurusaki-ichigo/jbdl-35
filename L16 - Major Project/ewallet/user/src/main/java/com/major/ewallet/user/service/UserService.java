package com.major.ewallet.user.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.major.ewallet.user.entity.UserInfo;
import com.major.ewallet.user.exceptions.DuplicateUserException;
import com.major.ewallet.user.repository.UserInfoRepository;
import com.major.ewallet.user.request.CreateUserRequestDto;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    UserInfoRepository repository;

    @Autowired
    KafkaTemplate<String , String> kafkaTemplate;

    private static final String USER_CREATED = "USER_CREATED";

    @Autowired
    ObjectMapper objectMapper;


    @Transactional(rollbackFor = Exception.class)
    public UserInfo createANewUser(CreateUserRequestDto requestDto){
        UserInfo transientUserInfo = requestDto.toUserInfo();
        Optional<UserInfo> byEmail = repository.findByEmail(transientUserInfo.getEmail());
        if(byEmail.isPresent()) {
            throw new DuplicateUserException();
        }
        return saveOrUpdate(transientUserInfo);
    }

    @SneakyThrows
    public void sendMessage(UserInfo userInfo){
        kafkaTemplate.send(USER_CREATED,objectMapper.writeValueAsString(userInfo));
    }

    private UserInfo saveOrUpdate(UserInfo userInfo){
       return repository.save(userInfo);
    }




}
