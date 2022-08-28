package com.example.mappings.mappings.repository;

import com.example.mappings.mappings.entities.Books;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Books, Long> {


    Optional<Books> findByNameIsLike(String name);


    Optional<Books> findByIsbn(String isbn);


    Page<Books> findByCreatedAtLessThan(LocalDateTime time, PageRequest pageRequest);

    /**
     * Interview question
     * whats the benefit of have composite index
     *  (A,B,C) --- (hint it give more than one index)
     *
     */

}
