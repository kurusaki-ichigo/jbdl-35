package com.example.springdata.data.controller;

import com.example.springdata.data.entity.UserInfo;
import com.example.springdata.data.requests.CreateUserDto;
import com.example.springdata.data.requests.UpdateUserDto;
import com.example.springdata.data.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


/***
 * There are 2 annotation to create a bean when annotated directly or indirectly
 * @Component --> create a object/bean of class
 *
 *          it calls the constructor of the class and creates an object;
 *
 * @Bean  --> create a bean of custom object created
 *
 *          -- it takes the object as bean of whatever returned by the method
 * */
@RestController
@Slf4j
public class MyFirstController {


    @Autowired
    UserService userService;


    @PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserInfo> addStudent(@Valid @RequestBody CreateUserDto userDto){
//        studentMap.put(student.getId(),student);
        log.info(" creating user {} ", userDto);
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }

    @PutMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserInfo> updateUser(@Valid @RequestBody UpdateUserDto userDto){
        log.info(" updating user {} ", userDto);
        return new ResponseEntity<>(userService.updateUser(userDto), HttpStatus.OK);
    }

    @GetMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<UserInfo>> getStudent(@RequestParam(value = "id" , defaultValue = "0") Integer id){
       return new ResponseEntity<>(userService.fetchStudentById(id), HttpStatus.OK);

    }


}
