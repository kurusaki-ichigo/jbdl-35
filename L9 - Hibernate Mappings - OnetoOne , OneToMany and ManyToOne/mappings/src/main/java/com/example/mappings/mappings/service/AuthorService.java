package com.example.mappings.mappings.service;

import com.example.mappings.mappings.entities.Authors;
import com.example.mappings.mappings.repository.AuthorRepository;
import com.example.mappings.mappings.requests.CreateBookRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;



    public Authors createAuthor(CreateBookRequest createBookRequest){
        Authors authors = createBookRequest.toAuthor();
        Optional<Authors> byEmail = authorRepository.findByEmail(authors.getEmail());
        if(byEmail.isEmpty()){
            return saveOrUpdateAuthor(authors);
        }
        return byEmail.get();
    }

    public Authors saveOrUpdateAuthor(Authors authors){
        return authorRepository.save(authors);
    }
}
