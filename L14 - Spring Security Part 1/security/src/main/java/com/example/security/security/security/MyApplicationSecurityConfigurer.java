package com.example.security.security.security;

import com.example.security.security.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Slf4j
public class MyApplicationSecurityConfigurer extends WebSecurityConfigurerAdapter {


    public static final String BAKER = "ROLE_BAKER";
    public static final String CUSTOMER = "ROLE_CUSTOMER";

    public static final String MERCHANT = "ROLE_MERCHANT";

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("Chef"));
        System.out.println(encoder.encode("cafe"));
        System.out.println(encoder.encode("whatPassword"));

    }

    /**
     * Authentication
     * 3 types
     * One would be in memory (coverd)
     * third
     * (there would be a different micorservice from which we will authn & authorize all requests)
     * <p>
     * Friends _ ?
     *
     * @param auth
     * @throws Exception
     */
//    @Override
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


    /**
     *
     * second
     * would involve a db
      * @param auth
     * @throws Exception
     */

    @Autowired
    BeanFactory beanFactory;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        UserService userService = (UserService) beanFactory.getBean("userService");
        auth.userDetailsService(userService);
    }


    @Bean
    PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    @Primary
    PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Authorization :
     *
     * @param http
     * @throws Exception
     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeHttpRequests()
//                .antMatchers(HttpMethod.GET, "/customer/**").hasAuthority(CUSTOMER)
//                .antMatchers("/merchant/**").hasAuthority(MERCHANT)
//                /**
//                 * this is wild card matcher
//                 *          --> it should be present towards the end of the ant mater
//                 */
//                .antMatchers("/**").permitAll()
//                .and()
//                .formLogin();
//    }
//


    /**
     * Authorization :
     *
     * @param http
     * @throws Exception
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
