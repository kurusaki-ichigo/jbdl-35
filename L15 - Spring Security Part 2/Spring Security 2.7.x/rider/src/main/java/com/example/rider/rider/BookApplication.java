package com.example.rider.rider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class BookApplication {

	/**
	 *
	 *
	 * Rider
	 * 		--> Book a cab
	 * 		--> rating
	 *
	 * Driver
	 *    --> accept a cab
	 *    --> rating
	 *
	 *
	 *
	 *    (User Microservice - 8080)  (U1 Driver, U2 Rider)
	 *
	 *
	 * 	 (Book Microservice - 9090)
	 *
	 * 		 BOOK (/rider) (emailId) -- only rider should be able to access it							USER
	 *
	 *						--------_> User (to fetch the details of the user by email)	--------------
	 *						<-----------------------------------------_User Details	<------
	 *
	 * 					--> injecting this user details
	 * 				into its own spring security filter chain
	 * 				--- and would only allow rider to use rider api
	 *
	 *
	 *
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(BookApplication.class, args);
	}

}
