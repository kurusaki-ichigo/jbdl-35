package com.example.security.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SecurityApplication {


	/**
	 * {@link ApplicationContext} -- which stores beans
	 *
	 * {@link  org.springframework.security.core.context.SecurityContext}
	 *
	 *  Authentication -- nothing layman (prinicipal) -- the user which has logged onto the system)
	 *
	 * Constraint -- the user should implement user details interface
	 *         (Spring)
	 *         SecurityContext
	 *
	 *
	 *         Spring be able to identify the custom user object - if the object implements A USER_DETAILS INTERFACE
	 *
	 *
	 */
	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}

}
