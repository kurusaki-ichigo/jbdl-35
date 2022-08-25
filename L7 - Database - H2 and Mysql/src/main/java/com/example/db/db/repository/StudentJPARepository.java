package com.example.db.db.repository;

import com.example.db.db.model.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentJPARepository extends JpaRepository<Student, Integer> {
}
