package com.example.mappings.mappings;


/**
 *
 *
 *
 *  Before starting anythings/ anyquestions
 *
 *          (Low Level Design)
 *          -> Draw around the entities (DB layer)
 *          -> focus on the functionalities you want to develop
 *          -> Relationships
 *          --> HLD (High level design)
 *
 *
 *          - Inventory (supply books)
 *          - Warehouse
 *          - Logistics
 *
 *      Chegg.com
 *
 *          -->  Entities / Actors
 *
 *          Read / rent books
 *          - User (user information) -- I have covered (spring security) - login and logout
 *          - Book
 *          - Order
 *          - Payment
 *          - Notification --- Many to Many
 *           - Wishlist / reviews and rating (later -- many to many)
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
 *      Many to One with respect to (books and user)
 *      one to Many
 *
 *      Audit log is -- ? maintianing history
 * @Audited + hibernate envers ---> read around()
 *
 * @Version -> have a distributed lock -> hibernate it would OptimisticLock / PessimisticLock
 *
 *              OptimisticLock
 *              -> lock is not acquired/ held but checked
 *
 *                  big billion day sale | walmart black friday sale.
 *
 *
 *    (1 million) ==             (Iphone 13 pro max ---> 10 quantities --> $300)
 *
 *              try it out --> how would you that
 *              queue of 10 items - divided in thread in 1 million
 *              1 instance can handle tomcat 200 threads - 250
 *              (250 thread) - 1 instance
 *              (250 thread) - 2 instance
 *
 *
 *              ------> cool ---> locking
 * @Version towards it
 *                  inventory(id  , quantity , version )
 *                      1, 10, 2
 *
 *                  --> 2 users in paraller - trying to perform operation -
 *                  update inventory set quanitity = quantity -1 , version = version + 1 where id = ? and version = ?
 *
 *                  Abhishek        --
 *                  update inventory set quanitity = quantity -1 , version = version + 1 where id = 1 and version = 2
 *
 *
 *                  Neeraj --
 *                  update inventory set quanitity = quantity -1 , version = version + 1 where id = 1 and version = 2
 *
 *
 *                  At the db -- they wiuld be applied sequentially
 *
 *                  update inventory set quanitity = quantity -1 , version = version + 1 where id = 1 and version = 2
 *                  (version = 3, quanity = 9 , id =  1)
 *
 *                  update inventory set quanitity = quantity -1 , version = version + 1 where id = 1 and version = 2
 *
 *
 *
 *
 *              PessimisticLock
 *              --> lock acquired around the object and held
 *              first decrease @Version and then would get commited --> its not commited -> result is not flushed.
 *              if(throw an expcetion - Optimisitic lock){
 *  *
 *  *                  }
 *  *
 *  *
 *
 *
 *      Redis --> question -> multithreaded (single threaded)
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
 *
 *
 * <p></>
 *
 *
 *
 *
 *
 */