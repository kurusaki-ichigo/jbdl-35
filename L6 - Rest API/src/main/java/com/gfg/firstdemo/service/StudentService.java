package com.gfg.firstdemo.service;

import com.gfg.firstdemo.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class StudentService {

    @Bean
    public RestTemplate createTemplate(){
        return new RestTemplate();
    }
}
