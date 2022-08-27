package com.example.mappings.mappings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MappingsApplication {

	/**
	 *          - User (user information) -- I have covered (spring security) - login and logout
	 *          - Book
	 *          - Order
	 *          - Payment
	 *          - Notification --- Many to Many
	 *
	 *
	 *
	 *          Book
	 *          	- Author (User)
	 *          Assumption
	 *          1 Book can have 1 author
	 *
	 *
	 *          Book ---- Author
	 *          1 --------> 1 Author
	 *          N<--------- 1Author
	 *          -----------------
	 *
	 *          N : 1
	 *
	 *          Book
	 *
	 *          ManyBookToOneAuthor
	 *          with respect to book
	 *          OneAuthorTOManyBooks
	 *
	 *
	 *
	 *          primary key  -- 1NF ()unique
	 *
	 *          foreign key --- primary key that is referencing another table - @JoinColumn
	 *
	 *		Book
	 *		(id, isbn, name , Author , author name, author email)
	 *		(B1, b1_isb1 , b1_name1, A1 , A1_name , A1_email)
	 *		(B2, b2_isb1 , b2_name1, A2 , A2_name , A2_email)
	 *		(B3, b3_isb1 , b3_name1, A1 , A1_name , A1_email)
	 *
	 * 	1) 15 -- Best Design
	 *	(B1, b1_isb1 , b1_name1 , A1 (foreign key) -- @JoinColumn)
	 *	(B2, b2_isb1 , b2_name1, A2)				(A1 , A1_name , A1_email)
	 *	(B3, b3_isb1 , b3_name1, A1)				(A2 , A2_name , A2_email)
	 *
	 *
	 *
	 * 2)   2
	 *	(B1, b1_isb1 , b1_name1 )
	 *	(B2, b2_isb1 , b2_name1)				(A1 , A1_name , A1_email, [B1, B3])
	 *	(B3, b3_isb1 , b3_name1)				(A2 , A2_name , A2_email, B2)



		3)
	 *	(B1, b1_isb1 , b1_name1 )
	 *	(B2, b2_isb1 , b2_name1)				(A1 , A1_name , A1_email)
	 *	(B3, b3_isb1 , b3_name1)				(A2 , A2_name , A2_email)

	 *																						(different table) -- many to many
	 *																						B1 A1
	 *																						B2	A2
	 *																						B3 A2
	 * 																						B1 A2
	 * 																						B3 A1
	 *
	 *
	 *
	 * H.W.	--	find or joining becomes expensive
	 *
	 *
	 * 			Order to Books
	 *
	 *
	 * 			1 unqiue book ------> how many order active can be placed (1 order)
	 *
	 * 		1 order can have ----> many books
	 *
	 *
	 *
	 *
	 * 	User to order mapping
	 * 	1 user -----> N orders
	 * 	1 user <---- 1 order
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *           - Wishlist / reviews and rating (later -- many to many)
	 *
	 *  functionalities
	 *     - Add to cart
	 *     - Rent a book / buy now
	 *     - Search -> search book by name (Like) , search book by isbn;
	 *     - Remove from cart
	 *     - SQL query (on the run)
	 *     - send reminder whenever you remove from cart (item) and so on (change when we will cover kafka)
	 *     - Want to check the history of the issuance of the book.  (how many people have rented this book)
	 *
	 *
	 *      (after covering whishlist )
	 *      - search by author (many to many)
	 *
	 *
	 * *          -> Relationships (bottle necks in case any)
	 *
	 *
	 *  Book -----  User
	 *  relationship (1nf 2nf 3nf bcnf)
	 *
	 *          one to one , one to many , many to one
	 *      Book to User
	 *     with resepct to book 1 book can be issued to how many user - 1 user (correct)
	 *
	 *      User to book
	 *      any number of books
	 *
	 *      Book ---- user
	 *      1 --->- 1
	 *      N ---<-- 1
	 *
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(MappingsApplication.class, args);
	}

}
