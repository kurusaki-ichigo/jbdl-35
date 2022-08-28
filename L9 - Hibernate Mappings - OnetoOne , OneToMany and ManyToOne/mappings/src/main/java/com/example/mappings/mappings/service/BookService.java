package com.example.mappings.mappings.service;


import com.example.mappings.mappings.entities.Authors;
import com.example.mappings.mappings.entities.Books;
import com.example.mappings.mappings.entities.Users;
import com.example.mappings.mappings.enums.StatusCodes;
import com.example.mappings.mappings.exceptions.UserExistsException;
import com.example.mappings.mappings.repository.AuthorRepository;
import com.example.mappings.mappings.repository.BookRepository;
import com.example.mappings.mappings.repository.UserRepository;
import com.example.mappings.mappings.requests.CreateBookRequest;
import com.example.mappings.mappings.requests.UpdateBookRequest;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;

    public Books createBook(CreateBookRequest bookRequest){
        Books book = bookRequest.toBook();
        Optional<Authors> byEmail = authorRepository.findByEmail(book.getAssociatedAuthor().getEmail());
        if(byEmail.isPresent()){
            return saveBook(book);
        }
        saveAuthor(book.getAssociatedAuthor());
        return saveBook(book);
    }

    public Books updateBook(UpdateBookRequest updateBookRequest){
        Optional<Books> book = bookRepository.findById(updateBookRequest.getId());
        if(book.isPresent()) {
            Books bookInfo = book.get();
            bookInfo.setName(updateBookRequest.getName());
            bookRepository.save(bookInfo);
        }
        throw new RuntimeException();
    }

    public Optional<Books> findByBookId(Long id){
        return bookRepository.findById(id);
    }

    private Books saveBook(Books books){
        return bookRepository.save(books);
    }

    private  Authors saveAuthor(Authors authors){
        return authorRepository.save(authors);
    }
}
