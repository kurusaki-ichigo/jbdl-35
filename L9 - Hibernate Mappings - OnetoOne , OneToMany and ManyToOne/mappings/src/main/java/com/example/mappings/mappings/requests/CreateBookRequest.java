package com.example.mappings.mappings.requests;

import com.example.mappings.mappings.entities.Authors;
import com.example.mappings.mappings.entities.Books;
import com.example.mappings.mappings.enums.BookStatus;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateBookRequest {

    @NotNull
    String name;
    @NotNull
    String isbn;

    @NotNull
    double amount;

    @NotBlank
    String authorEmail;

    @NotNull
    String authorName;

    public Authors toAuthor(){
        return Authors.builder()
                .email(authorEmail)
                .name(authorName)
                .build();
    }
    public Books toBook() {
        return Books.builder()
                .name(name)
                .isbn(isbn)
                .amount(amount)
                .associatedAuthor(toAuthor())
                .build();
    }
}
