package com.example.rider.rider.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@Slf4j
public class SecurityConfiguration {





    @Autowired
    BeanFactory beanFactory;


    /**
     * 16 filter chains
     *
     *  (Authenticaiton)            |           |   |           |    (Authorization)
     *
     * @param http
     * @return
     * @throws Exception
     */


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        UserSecurityFilter userSecurityFilter = (UserSecurityFilter) beanFactory.getBean("userSecurityFilter");
        http
                .csrf().disable()
                .authorizeHttpRequests()
                .antMatchers("/**").permitAll()

                .and()
                .addFilterBefore(userSecurityFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }





}
