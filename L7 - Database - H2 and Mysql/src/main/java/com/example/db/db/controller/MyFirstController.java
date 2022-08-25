package com.example.db.db.controller;

import com.example.db.db.model.entities.Student;
import com.example.db.db.model.requests.CreateStudentDto;
import com.example.db.db.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;


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
    StudentService service;



    @GetMapping("/greet")
    public String greetUser(@RequestParam("name") String name){
        return "Hello "+ name.toUpperCase() + " !!";
    }

    @GetMapping("/greetnew/{name}/{lastname}")
    public String greetNew(@PathVariable("name") String name,@PathVariable("lastname") String lastName){
        return "Hello "+ name.toLowerCase() + " "+lastName+" !!";
    }


    @PostMapping("/student")
    public ResponseEntity<Student> addStudent(@Valid @RequestBody CreateStudentDto student){
//        studentMap.put(student.getId(),student);
        log.info(" creating student {} ", student);
        return new ResponseEntity<>(service.saveStudent(student), HttpStatus.CREATED);
    }

    @GetMapping("/student")
    public ResponseEntity<Optional<Student>> getStudent(@RequestParam(value = "id" , defaultValue = "0") Integer id){
       return new ResponseEntity<>(service.fetchStudentById(id), HttpStatus.OK);

    }


    public static void main(String[] args) {
        System.out.println(UUID.randomUUID());
    }
}
