package com.example.db.db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DbApplication {

	/**
	 *
	 *	(DB) -- storage unit
	 *
	 *
	 * Relational vs Non Relational
	 *
	 *-------------> Relational
	 *
	 * 	Mysql , postgres, Oracle db , cockroach
	 *	H2 --> in memory database
	 *
	 * ACID - properties --
	 * 				(Atomicity , Consistency , Isolation , Durability)
	 * 		Atomicity
	 * 		Consistency
	 *
	 *
	 *
	 * @Transactional
	 *-------------> NonRelational
	 *
	 * 	insert ---> id
	 * 	read that (id)-->			more than half of the node should return a value.
	 *
	 *	Mongo , Cassandra , Dynamo
	 *
	 * 	Mongo - 4.3 + do support transactions
	 * 	DynamoDb - it also support transaction but with same region across one or multiple tables.
	 *
	 *
	 *
	 *
	 * 			Youtube
	 * 				-->
	 * 				MYSQL first ()		--- slave
	 * 			(master -- slave)
	 * 				multiple region DR -->
	 *
	 * 		scale efficiently -- > they created there own flavour of mysql - Vitess (db).
	 *
	 *
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(DbApplication.class, args);
	}

}
