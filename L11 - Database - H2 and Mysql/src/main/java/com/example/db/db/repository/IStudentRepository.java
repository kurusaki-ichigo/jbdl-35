package com.example.db.db.repository;

import com.example.db.db.model.entities.Student;

import java.util.Optional;
import java.util.UUID;

public interface IStudentRepository {
    Student save(Student student);

    Optional<Student> findById(Integer uuid);
}
