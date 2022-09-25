package com.major.ewallet.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserApplication {

	/**
	 *
	 * Create a user
	 * 			-- send a notification to user -- Hey account has been created
	 * 		---> create a wallet
	 * 				--> CREATE a transaction in pending state
	 * 							(User A ---> User B)					User A in this case is our system
	 * 					---> top up the wallet with 10 bucks (promo balance)
	 * 				--> Mark the transaction success
	 * 							---> trigger a notification
	 *
	 *
	 * 1)
	 * 		FE --> Create A User
	 * 		FE --> Create A Wallet (UserId in request params)
	 * 	2)
	 * 		FE --> Create A User
	 * 					---> creates a wallet
	 *
	 *
	 *
	 * 	Service A (User) --- Service B (wallet)
	 *
	 *
	 *
	 *
	 *
	 *		DB schema
	 *		{
	 *			- user Id
	 *			- name
	 *			- email
	 *			- contactNumber
	 *		}
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

}
