package com.example.db.db.model.requests;


import com.example.db.db.model.entities.Student;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateStudentDto {

    @NotNull
    private String name;
    @Positive
    private Integer age;


    public Student toStudent(){
        return Student.builder()
                .age(age)
                .name(name).build();
    }

}
