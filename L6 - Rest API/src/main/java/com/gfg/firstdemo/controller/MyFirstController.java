package com.gfg.firstdemo.controller;

import com.gfg.firstdemo.domain.Student;
import com.gfg.firstdemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


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
public class MyFirstController {


/**
 * GET   -> to retrieve the resource
 * POST  -> to create a new resource
 * PUT   -> update with complete resource --> // UPDATE
 * PATCH -> update the attribute of resource
 * DELETE -> Delete a resource
 * */





/** 2 ways to let the spring boot notify about the type of request
 * 1. old way : @RequestMapping
 * 2. Specific type annotation: @GETMapping,@PostMapping..
 * */


/**There are 2 ways to read Get param,
 * One is via request parameter
 *  ex: abc.com?name=<VALUE>
 *  Two is via URL value
 *  ex: abc.com/<VALUE>/
 *
 * */


/**
 * Requests decoding and mapping
 * 1. DispatcherServlet -> Decodes the request
 * 2. RequestMappingHandlerMapping -> contains map of request mapping and provides the mapping when called by dispatcher servlet
 *
 * */

//    Adds a persistent storage
    Map<Integer,Student> studentMap=new HashMap<>();

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

//    @RequestMapping(value="/greetOld",method= RequestMethod.GET)
//    public String greetUserOld(@RequestParam("name") String name){
//        return "Hello "+ name.toUpperCase() + " !!";
//    }

    @PostMapping("/student/add")
    public ResponseEntity<Student> addStudent(@RequestBody Student student){
        studentMap.put(student.getId(),student);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @GetMapping("/student")
    public Student getStudent(@RequestParam("id") Integer id){
       return studentMap.getOrDefault(id,null);

    }

    /**
     * for all the APIs steps:
     *
     *  Types of API:
     https://apisyouwonthate.com/blog/understanding-rpc-rest-and-graphql
     *
     * 1. Finalize the contract (example: swagger)
     *      a. rest endpoint
     *      b. http method
     *      c. status codes
     *      d. exceptions, error message or redirections
     * */

/**
 * 127.0.0.1:8080 --> locahost
 *
 * REST - stateless
 *   Ex: 1 ( Stateful example)
 *   abc.com/records  --> 1 - 10 (100)
 *   abc.com/records  -->10 - 20 (100)
 *   abc.com/records --> 20- 30
 *
 *   Ex:2 (Stateless example)
 *   abc.com/records?start=0&end=10  --> 1 - 10 (100)
 *   abc.com/records?start=10&end=20  --> 10 - 20 (100)
 *
 *
 *
 * */


/**
 *  1.GET  --> retrieve the information from the server.uses the URL to send the data or input
 *  2.POST --> create a new resource in the system.
 *  3.DELETE --> Delete or remove a resource from the system
 *  4.PUT   --> update an attribute of the resource.
 *  5.PATCH --> update an attribute of the resource.
 *  OPTIONS,HEAD, TRACE..
 *
 *  1. safety ( state changes in the resource)  2. Idempotent -> no unwanted change
 *
 *  GET/OPTIONS/HEAD ->    Safe, Idempotent
 *  POST ->   Unsafe, yes/no
 *  DELETE -> Unsafe, Idempotent  Example: (delete student roll no. 1) (15 times)
 *  PUT  -> Unsafe, non-Idempotent Example: (Rolln0.2 age:10)
 *  PATCH -> Unsafe, Idempotent
 *
 *
 *  Student:roll no.1, name: amit
 *  put --> change the roll no, 1 to 2
 *  patch --> add new attribute
 *          roll no.1, name: amit, lastname: sharma
 *
 * **/

/**
 * Status code:
 *
 * link: https://developer.mozilla.org/en-US/docs/Web/HTTP/Status
 * */


/**
 * Types of logger
 *
 *  1.Trace ->
 *  2.Debug
 *  3.Info
 *  4.Warn
 *  5.Error
 *
 * */








}
