package com.example.payment.payment.security;

import com.example.payment.payment.UserInfo;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("UserSecurityFilter")
@Slf4j
public class UserSecurityFilter extends OncePerRequestFilter {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    Gson gson;


    /**
     *
     * General example of how microservices interact with spring security
     *
     *
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String email = request.getHeader("email");
        ResponseEntity<String> responseReceived = restTemplate.getForEntity("http://localhost:8080/user/" + email, String.class);
        log.info(" response received {} ", responseReceived);
        UserInfo userInfo = gson.fromJson(responseReceived.getBody(), UserInfo.class);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userInfo, null, userInfo.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        filterChain.doFilter(request, response);
    }
}
