package com.example.mappings.mappings.service;

import com.example.mappings.mappings.entities.Users;
import com.example.mappings.mappings.enums.StatusCodes;
import com.example.mappings.mappings.exceptions.InvalidInputException;
import com.example.mappings.mappings.exceptions.UserExistsException;
import com.example.mappings.mappings.exceptions.UserInvalidException;
import com.example.mappings.mappings.repository.UserRepository;
import com.example.mappings.mappings.requests.CreateUserRequest;
import com.example.mappings.mappings.requests.UpdateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
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
        return findUserIfExistsByEmail(email);
    }

    public Users updateUser(UpdateUserRequest userRequest){
        Users userInfo = findExistingUserByEmail(userRequest.getEmail());
        userInfo.setName(userRequest.getName());
        return saveOrUpdate(userInfo);
    }

    public void deleteUser(String email){
        delete(findExistingUserByEmail(email));
    }

    /**
     * <p>
     *     this method is used to fetch the user if exists and
     *     if a user does not it throws exception
     * </p>
     * @param email
     * @return
     * @apiNote : throws {@link  UserInvalidException}
     */
    public Users findExistingUserByEmail(String email) {
        Optional<Users> byEmail = findUserIfExistsByEmail(email);
        if(byEmail.isEmpty()){
            throw new UserInvalidException(StatusCodes.USER_DOES_NOT_EXISTS);
        }
        return byEmail.get();
    }

    private Optional<Users> findUserIfExistsByEmail(String email) {
        if(Objects.isNull(email) || email.isBlank()){
            throw new InvalidInputException(StatusCodes.INVALID_INPUT);
        }
        return userRepository.findByEmail(email);
    }


    private Users saveOrUpdate(Users users){
       return userRepository.save(users);
    }

    private void delete(Users users){ userRepository.delete(users); }


}
