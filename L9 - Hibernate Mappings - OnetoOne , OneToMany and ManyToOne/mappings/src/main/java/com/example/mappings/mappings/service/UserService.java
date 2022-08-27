package com.example.mappings.mappings.service;

import com.example.mappings.mappings.entities.Users;
import com.example.mappings.mappings.enums.StatusCodes;
import com.example.mappings.mappings.exceptions.BaseException;
import com.example.mappings.mappings.exceptions.InvalidInputException;
import com.example.mappings.mappings.exceptions.UserExistsException;
import com.example.mappings.mappings.exceptions.UserInvalidException;
import com.example.mappings.mappings.repository.UserRepository;
import com.example.mappings.mappings.requests.CreateUserRequest;
import com.example.mappings.mappings.requests.UpdateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public Users createUser(CreateUserRequest userRequest){
        Users users = userRequest.toUser();
        Optional<Users> byEmail = userRepository.findByEmail(users.getEmail());
        if(byEmail.isPresent()){
            throw new UserExistsException(StatusCodes.USER_ALREADY_EXISTS);
        }
       return saveOrUpdate(users);
    }

    public Optional<Users> getUser(String email){
        /**
         * email can be null then ---> exception
         */
        if(email.isEmpty()){
            throw new InvalidInputException(StatusCodes.INVALID_INPUT);
        }
        Optional<Users> byEmail = userRepository.findByEmail(email);
        if(byEmail.isEmpty()){
            throw new UserInvalidException(StatusCodes.USER_DOES_NOT_EXISTS);
        }
        return byEmail;
    }

    public Users updateUser(UpdateUserRequest userRequest){
        Optional<Users> byEmail = userRepository.findByEmail(userRequest.getEmail());
        if(byEmail.isEmpty()){
            throw new UserInvalidException(StatusCodes.USER_DOES_NOT_EXISTS);
        }

        Users userInfo = byEmail.get();
        userInfo.setName(userRequest.getName());
        return saveOrUpdate(userInfo);
    }

    public String deleteUser(String email){
        if(email.isEmpty()){
            throw new InvalidInputException(StatusCodes.INVALID_INPUT);
        }
        Optional<Users> byEmail = userRepository.findByEmail(email);
        if(byEmail.isEmpty()){
            throw new UserInvalidException(StatusCodes.USER_DOES_NOT_EXISTS);
        }
        Users userInfo = byEmail.get();
        delete(userInfo);

        /**
         * kindly extract static strings 
         */
        return "User deleted for email: " + email;
    }



    private Users saveOrUpdate(Users users){
       return userRepository.save(users);
    }

    private void delete(Users users){ userRepository.delete(users); }


}
