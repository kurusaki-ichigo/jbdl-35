package com.example.mappings.mappings.service;

import com.example.mappings.mappings.entities.Authors;
import com.example.mappings.mappings.entities.Books;
import com.example.mappings.mappings.enums.BookStatus;
import com.example.mappings.mappings.enums.StatusCodes;
import com.example.mappings.mappings.exceptions.AuthorNotFoundException;
import com.example.mappings.mappings.exceptions.BookNotFoundException;
import com.example.mappings.mappings.repository.BookRepository;
import com.example.mappings.mappings.requests.CreateBookRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.awt.print.Book;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class BookService {


    @Autowired
    AuthorService authorService;

    @Autowired
    BookRepository repository;

    /**
     * rest template - while creating notification today
     *
     * @param bookRequest
     * @return
     */
    public Books createBook(CreateBookRequest bookRequest) {
        Authors author = authorService.createAuthor(bookRequest);
        if (Objects.isNull(author)) {
            throw new AuthorNotFoundException(StatusCodes.AUTHOR_NOT_FOUND);
        }
        Books books = bookRequest.toBook();
        books.setAssociatedAuthor(author);
        return saveOrUpdate(books);
    }

    private Books saveOrUpdate(Books books) {
        return repository.save(books);
    }


    public List<Books> issueBookAndPersist(List<Books> books){

        books.forEach(book -> {
            book.setBookStatus(BookStatus.ISSUED);
            saveOrUpdate(book);
        });
        return books;
    }

    /**
     *
     * Trip advisor
     *  - polling backend -- ? why ??
     *
     *          not used caching ( -- yes)
     *
     *
     *
     *   Back ground
     *          -------> Searcher API ----> 1 (there are many searchers)
     *
     *                  ---> Amadeus (search results) - 50 (T1) = 50 seconds
     *                  ---> Sabre (search results)- 50      (T2) = 20 seconds
     *                  ---> Galileo  (search results) - 50  (T3) = 50 seconds
     *
     *                  (Time) -- parallel
     *                  RestTemplate
     *                  main thread --> 3 parallel threads
     *                                      1 Amadeus             ConnectionPooling
     *                                      2 Sabre               ConnectionPooling
     *                                      3 Galileo             ConnectionPooling
     *
     *
     *
     *                FE ---------------- BE
     *                      <-----      [] rety after 5 seconds
     *                      ---->>>>>>[do you got results]
     *                      <-----      [] rety after 5 seconds
     *      *                           [50 objects received after 20seconds]
     *
     *
     *
     *
     *              1 million users
     *              Fe --> get All users        BE ------- > >>>>>>>    (DB)
     *                                                  (1 million users)
     *                                          BE   <--- TCP layer (all the packets) 1Million MB
     *
     *                                          20 objects <---- sorted by some attribute (Sorted by name / DESC sorted by created Timstamp)
     *
     *
     *
     *
     *
     *      DB connection pooling
     *          --- Parallel Connections
     *              opneing and closing of a connection is a heavy task
     *              Pool of connection with driver and DB(  20 connections -- open / idle state)
     *
     *              Querying a db --> we aquire the connection
     *
     *      Http connection pooling
     *          --- Parallel Connections
     *              opneing and closing of a connection is a heavy task
     *              Socket timeout          (Pranav) tomcat server 200 threads - 250n threads       ----route--   (Ankit)
     *                                                      --route---           Bonic
     *                                                          ---route--       Akaash
     *
     *
     *              Read Timeout
     *              Request Timeout
     *              Max per route  -- Http route -- www.abc.com:443 -- https
     *                                                          :8080 -- http
     *              Min per route
     *




     *                  Sum of results
     *                      --> invoke a book
     *
     *
     *          These searchers provides result with various airlines
     *
     *
     *
     *
     *
     * @return
     */
    public Page<Books> fetchAllBooks(Integer pageNumber){
        Sort sort = Sort.by(Sort.Direction.DESC, "name");
        PageRequest pageRequest = PageRequest.of(pageNumber, 20);
        return repository.findByCreatedAtLessThan(LocalDateTime.now(), pageRequest);
    }

    /**
     *
     * @param isbn
     * @return
     */
    public Books findBookByIsbn(String isbn){
        Optional<Books> byIsbn = repository.findByIsbn(isbn);
        if(byIsbn.isEmpty()) {
            throw new BookNotFoundException(StatusCodes.INVALID_INPUT);
        }
        return byIsbn.get();
    }

    public void makeBooksAvailable(List<Books> booksList) {
        booksList.forEach(book -> {
            book.setBookStatus(BookStatus.AVAILABLE);
            saveOrUpdate(book);
        });
//        return booksList;
    }
}
