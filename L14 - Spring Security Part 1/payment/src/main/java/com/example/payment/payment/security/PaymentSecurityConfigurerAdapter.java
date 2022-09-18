package com.example.payment.payment.security;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class PaymentSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {


//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        this.disableLocalConfigureAuthenticationBldr = true;
//    }

    @Autowired
    BeanFactory beanFactory;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        UserSecurityFilter userSecurityFilter = (UserSecurityFilter) beanFactory.getBean("UserSecurityFilter");

        http
                .csrf().disable()
                .authorizeHttpRequests()
                .antMatchers("/**").permitAll()

                .and()
                .addFilterBefore(userSecurityFilter, UsernamePasswordAuthenticationFilter.class);
    }



}
