package com.example.mappings.mappings.controller;

import com.example.mappings.mappings.entities.Books;
import com.example.mappings.mappings.requests.CreateBookRequest;
import com.example.mappings.mappings.requests.UpdateBookRequest;
import com.example.mappings.mappings.service.UpdatedBookService;
import com.example.mappings.mappings.utils.ResponseGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@Slf4j
public class BookControllerV2 {

    @Autowired
    UpdatedBookService updatedBookService;

    @Autowired
    ResponseGenerator responseGenerator;

    @PostMapping(value = "/v2/book", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createABook(@Valid @RequestBody CreateBookRequest bookInfo) {
        log.info("Request Received {} ", bookInfo);
        return responseGenerator.generateResponse(updatedBookService.createBook(bookInfo), HttpStatus.CREATED);
    }
}
