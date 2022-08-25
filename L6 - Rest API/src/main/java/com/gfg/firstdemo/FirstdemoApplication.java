package com.gfg.firstdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class FirstdemoApplication implements CommandLineRunner {

	public static void main(String[] args) {

		SpringApplication.run(FirstdemoApplication.class, args);
	}

	@Autowired
	ApplicationContext context;

	@Override
	public void run(String... args) throws Exception {

		if(null != context){
			System.out.println("bean names");
			Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
		}

	}


	/**
	 * Logging levels.
	 * 1. Trace
	 * 2. Debug
	 * 3. Info
	 * 4. Warn
	 * 5. Error
	 * */
}



















