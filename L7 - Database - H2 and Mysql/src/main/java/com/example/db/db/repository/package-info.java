package com.example.db.db.repository;


/**
 *
 *  (RequestObject) ---->       (Database Object)
 *               (change)       --> (change)
 *
 *
 *       ORM (Object Relational Mapping Patterns has been introduced)
 *       (Student)
 *          (Inheritence)
 *
 *
 *          (User)
 *
 *      (Student)       (Teacher)       (Head of Department)
 *
 *      (abstract this layer out)
 *          (Domain Object) |-----> Persistent Object
 *
 *          Spring Data JPA
 *          JPA
 *          - Java Persistnece APi
 *          2019 onwards spring 17+ , 18
 *          Jakarta
 *
 *
 *    Disadvantages of using old way
 *    - more prone to error
 *    - in terms of maintainence
 *    - conversionn of java object to resultset
 *
 *    aws instance = 12000 conncurrent connections
 *
 *    - per application instance (100 connections)
 *    (3 such applications)
 *    atleast (300 connections would be active , but cached in connectionpool)
 *    there are consumers / applications reading from our database - consumers alson involve using connections
 *
 *
 *	SpringBoot Application											MySQL (relation)
 *	Server															Server
 *Usertable                                                     user_table in DB
 * id                                               id
 * email                                            email
 *
 * pojo mapping needs to be done explicitly in the old
 *  (serilzation  and deserialization)
 *
 *  Abstract layer (mapping of the entity with the server table)
 *  (Hibernate)
 *      ---> ORM (object relational mapping)
 *
 *
 *
 *
 *	Spring-data-starter-jpa
 *	Spring data jpa
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */