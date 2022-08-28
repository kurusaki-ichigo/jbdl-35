package com.example.mappings.mappings.repository;

import com.example.mappings.mappings.entities.Books;
import com.example.mappings.mappings.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Books, Long> {

    @Query(value = "select name from books where book_id = :bookId", nativeQuery = true)
    Optional<Books> findById(@Param("bookId") Long bookId);
}
