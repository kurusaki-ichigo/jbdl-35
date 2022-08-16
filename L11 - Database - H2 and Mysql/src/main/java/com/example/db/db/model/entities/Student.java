package com.example.db.db.model.entities;

import lombok.*;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@Entity
public class Student implements Serializable {


//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    private String name;
    private Integer age;


}
