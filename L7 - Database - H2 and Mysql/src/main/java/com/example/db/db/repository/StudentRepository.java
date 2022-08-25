package com.example.db.db.repository;

import com.example.db.db.model.entities.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository("mapRepository")
@Slf4j
public class StudentRepository implements IStudentRepository{

    /**         (request mapping)       (business mapping)              Data layer             ()
     * FE --> (@Controller)--->         (@Service)      ------->        (@Repository)   -----> DB
     */

    Map<Integer, Student> hashMap = new HashMap<>();

    @Override
    public Student save(Student student){
        // persist
        log.info(" saving user {} ", student);
        hashMap.put(student.getId(), student);
        log.info(" map {} ", hashMap);
        return student;
    }


    @Override
    public Optional<Student> findById(Integer uuid){
        return Optional.ofNullable(hashMap.get(uuid));
    }




}
