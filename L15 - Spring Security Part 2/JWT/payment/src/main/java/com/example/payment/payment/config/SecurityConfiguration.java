package com.example.payment.payment.config;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration {


     @Autowired
    BeanFactory beanFactory;

    /**
     * JWT S2S verification
     *
     */

    @Bean
    @Order(1)
    SecurityFilterChain jwtFilterChain(HttpSecurity http) throws Exception {
        JwtFilter jwtFilter = (JwtFilter) beanFactory.getBean("jwtFilter");
        http.csrf().disable();
        http.requestMatchers().antMatchers("/jwt/**")
                .and()
                .authorizeHttpRequests()
                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    /**
     * User Session
     * UserFilter -- FE calls (where user token would be present)
     */
    @Bean
    @Order(2)
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


    /**
     *
     * (Either the request would be by FE)
     *
     *      or would be made by internal service
     *
     *      2 security filter chains
     *
     *      /jwt/** ---> router for JWT Filter
     (/***) --> other services would be routed for (UserSecurityFilter)
     *
     *
     *
     *
     *  Defining order of the beans of similar type
     *
     *
     * @param http
     * @return
     * @throws Exception
     */


}
