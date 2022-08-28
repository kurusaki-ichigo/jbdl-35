package com.example.mappings.mappings.controller;


import com.example.mappings.mappings.entities.Books;
import com.example.mappings.mappings.requests.CreateBookRequest;
import com.example.mappings.mappings.requests.UpdateBookRequest;
import com.example.mappings.mappings.service.BookService;
import com.example.mappings.mappings.service.UpdatedBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.print.Book;
import java.util.Optional;

@RestController
@Slf4j
public class BookController {

    @Autowired
    BookService bookService;


    @PostMapping(value = "/book", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Books> createABook(@Valid @RequestBody CreateBookRequest bookInfo) {
        log.info("Request Received {} ", bookInfo);
        return new ResponseEntity<>(bookService.createBook(bookInfo), HttpStatus.CREATED);
    }

    @PutMapping(value = "/book", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Books> updateUser(@Valid @RequestBody UpdateBookRequest updateBookRequest){
        log.info(" updating book {} ", updateBookRequest);
        return new ResponseEntity<>(bookService.updateBook(updateBookRequest), HttpStatus.OK);
    }

    @GetMapping(value = "/book", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Books>> getBook(@RequestParam(value = "id", defaultValue = "0") Long id) {
        return new ResponseEntity<>(bookService.findByBookId(id), HttpStatus.OK);
    }
}
