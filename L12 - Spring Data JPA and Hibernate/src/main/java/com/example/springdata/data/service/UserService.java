package com.example.springdata.data.service;

import com.example.springdata.data.entity.UserInfo;
import com.example.springdata.data.exceptions.ExceptionStatusCodes;
import com.example.springdata.data.exceptions.InvalidInputException;
import com.example.springdata.data.repository.UserInfoRepository;
import com.example.springdata.data.requests.CreateUserDto;
import com.example.springdata.data.requests.UpdateUserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    /**
     * FE --> request --> add logic --> query database
     *
     * @Controller          @Service    @Repository
     *
     *          (@Component)
     */
    @Autowired
    UserInfoRepository userInfoRepository;


    public UserInfo createUser(CreateUserDto userDto){
        UserInfo userInfo = userDto.toUserInfo();
       return saveOrUpdate(userInfo);
    }



    public UserInfo updateUser(UpdateUserDto userDto){
        Optional<UserInfo> byUserIdentifier = userInfoRepository.findByUserIdentifier(userDto.getUuid());
        if(byUserIdentifier.isEmpty()){
            throw new InvalidInputException(ExceptionStatusCodes.INVALID_USER);
        }

        UserInfo userInfo = byUserIdentifier.get();
        userInfo.setName(userDto.getName());
        return saveOrUpdate(userInfo);
    }



    private UserInfo saveOrUpdate(UserInfo userInfo){
        return userInfoRepository.save(userInfo);
    }


    public Optional<UserInfo> fetchStudentById(Integer id) {
        if(Objects.isNull(id)){
            throw new InvalidInputException(ExceptionStatusCodes.INVALID_INPUT);
        }

        Optional<UserInfo> byId = userInfoRepository.findById(Long.valueOf(id));
        if(byId.isEmpty()){
            throw new InvalidInputException(ExceptionStatusCodes.INVALID_USER);
        }
        return byId;
    }
}
