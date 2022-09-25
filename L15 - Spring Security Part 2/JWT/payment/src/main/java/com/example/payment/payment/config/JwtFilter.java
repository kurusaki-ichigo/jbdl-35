package com.example.payment.payment.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.payment.payment.entity.JWTClient;
import com.example.payment.payment.entity.S2SCleint;
import com.example.payment.payment.repository.JwtRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    JwtRepository jwtRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        /**
         *
         *  JWT CLIENT  ---Entitiy similar to user
         *
         *
         */

        String jwtToken = request.getHeader("Authorization");
        /**
         * remove bearer and decode remaining
         */
        jwtToken = jwtToken.replace("Bearer ", "");

        DecodedJWT decode = JWT.decode(jwtToken);
        String issuer = decode.getIssuer();
        /**
         * fetch the issuer from DB
         *
         * JWT repository --> fetchByS2SClient(Client.value(issuer))
         *
         *  Production ---> leeway +- 20 seconds (accept the token or else reject)
         *  algo - > we have in DB vs in token
         *  -- we also decrypt using password
         *
         *
         *
         *  UserFilter --> (UserROles)
         *
         *  S2SClient --> (Client's roles)
         *
         *
         *
         */


        Optional<JWTClient> jwtClient = jwtRepository.findByClient(S2SCleint.valueOf(issuer));
        if(jwtClient.isPresent()){
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(jwtClient.get(), null, jwtClient.get().getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }

        /**
         * else create anonymous user
         */

        filterChain.doFilter(request, response);
    }
}
