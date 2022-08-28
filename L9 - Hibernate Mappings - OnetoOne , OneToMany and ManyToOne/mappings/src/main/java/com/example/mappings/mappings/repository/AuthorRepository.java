package com.example.mappings.mappings.repository;

import com.example.mappings.mappings.entities.Authors;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Authors, Long> {

    Optional<Authors> findByEmail(String email);
}