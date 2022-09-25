package com.example.demo.config;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import java.util.LinkedList;
import java.util.List;

public class SecurityConfigurationOld extends WebSecurityConfigurerAdapter {

    public static final String BAKER = "ROLE_BAKER";
    public static final String CUSTOMER = "ROLE_CUSTOMER";

    public static final String MERCHANT = "ROLE_MERCHANT";

    /**
     *
     * Authentication
     *
     * @param auth
     * @throws Exception
     */
//        @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("Monika")
//                .password("$2a$10$RrhY25SmEsTlI0Riz7ViuuOF84WLLoU0mq8xAdT6nD/VwTfK2MoVi")
//                .authorities(BAKER, CUSTOMER)
//                .and()
//                .withUser("Gunter")
//                .password("$2a$10$F9.8nkq.Tt0.O09J/Kf/u.K8HweajUdc.BmLIxDZVZ2csXYhe/Cbq")
//                .authorities(MERCHANT)
//                .and()
//                .withUser("Joey")
//                .password("$2a$10$eFczvQySo3AhQDbBIP2n4OenQiGB0wjkqcXQ4vk3Vnt/wP2afqgby")
//                .authorities(CUSTOMER);
//    }


//    InMemoryUserDetailsManager userDetailsManager(){
//        UserDetails monika = User.withUsername("Monika")
//                .password("{bcrypt}$2a$10$RrhY25SmEsTlI0Riz7ViuuOF84WLLoU0mq8xAdT6nD/VwTfK2MoVi")
//                .authorities(BAKER, CUSTOMER).build();
//
//        UserDetails gunter = User.withUsername("Gunter")
//                .password("{bcrypt}$2a$10$F9.8nkq.Tt0.O09J/Kf/u.K8HweajUdc.BmLIxDZVZ2csXYhe/Cbq")
//                .authorities(MERCHANT).build();
//
//        UserDetails joey = User.withUsername("Joey")
//                .password("{bcrypt}$2a$10$eFczvQySo3AhQDbBIP2n4OenQiGB0wjkqcXQ4vk3Vnt/wP2afqgby")
//                .authorities(CUSTOMER).build();
//        List<UserDetails> userDetailsList = new LinkedList<>();
//        userDetailsList.add(monika);
//        userDetailsList.add(gunter);
//        userDetailsList.add(joey);
//        return new InMemoryUserDetailsManager(userDetailsList);
//    }

    @Autowired
    BeanFactory beanFactory;
    @Autowired
    UserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        UserService userService = (UserService) beanFactory.getBean("userService");
        auth.userDetailsService(userService);
    }


    /**
     *
     * Authorization
     *
     *
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests()
                .antMatchers("/**").permitAll()

                .and()
                .formLogin()
        ;
    }


}
