package com.example.mappings.mappings.controller;

import com.example.mappings.mappings.requests.CreateBookRequest;
import com.example.mappings.mappings.service.BookService;
import com.example.mappings.mappings.utils.ResponseGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
public class BooksController {


    @Autowired
    BookService bookService;

    @Autowired
    ResponseGenerator responseGenerator;

    @PostMapping(value = "/v2/book", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createABook(@Valid @RequestBody CreateBookRequest bookInfo) {
        log.info("Request Received {} ", bookInfo);
        return responseGenerator.generateResponse(bookService.createBook(bookInfo), HttpStatus.CREATED);
    }

    @GetMapping(value = "/v2/book", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> fetchABookByIsbn(@RequestParam(value = "isbn") String isbn) {
        log.info("Request Received {} ", isbn);
        return responseGenerator.generateResponse(bookService.findBookByIsbn(isbn), HttpStatus.OK);
    }

    @GetMapping(value = "/v2/books", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> fetchAll(@RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber) {
        log.info("Request Received {} ", pageNumber);
        return responseGenerator.generateResponse(bookService.fetchAllBooks(pageNumber), HttpStatus.OK);
    }

}
