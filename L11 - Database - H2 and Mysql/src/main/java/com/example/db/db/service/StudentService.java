package com.example.db.db.service;

import com.example.db.db.model.entities.Student;
import com.example.db.db.model.requests.CreateStudentDto;
import com.example.db.db.repository.IStudentRepository;
import com.example.db.db.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class StudentService {


    @Autowired
    @Qualifier("h2Repository")
    IStudentRepository studentRepository;


    public Student saveStudent(CreateStudentDto request){
        Student student = request.toStudent();
        log.info(" student instance {} ", student);
        Student save = studentRepository.save(student);
        log.info(" saved student {} ", save);
        return save;
    }

    public Optional<Student> fetchStudentById(Integer studentId){
        return studentRepository.findById(studentId);
    }
}
