package com.example.mappings.mappings.service;

import com.example.mappings.mappings.entities.Authors;
import com.example.mappings.mappings.entities.Books;
import com.example.mappings.mappings.enums.StatusCodes;
import com.example.mappings.mappings.exceptions.AuthorNotFoundException;
import com.example.mappings.mappings.repository.BookRepository;
import com.example.mappings.mappings.requests.CreateBookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UpdatedBookService extends BookService {


    @Autowired
    AuthorService authorService;

    @Autowired
    BookRepository repository;

    @Override
    public Books createBook(CreateBookRequest bookRequest){
        Authors author = authorService.createAuthor(bookRequest);
        if(Objects.isNull(author)){
            throw new AuthorNotFoundException(StatusCodes.AUTHOR_NOT_FOUND);
        }
        Books books = bookRequest.toBook();
        books.setAssociatedAuthor(author);
        return saveOrUpdate(books);
    }



    private Books saveOrUpdate(Books book){
        return repository.save(book);
    }

}
