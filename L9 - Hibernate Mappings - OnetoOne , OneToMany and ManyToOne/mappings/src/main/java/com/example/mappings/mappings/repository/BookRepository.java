package com.example.mappings.mappings.repository;

import com.example.mappings.mappings.entities.Books;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Books, Long> {
}
