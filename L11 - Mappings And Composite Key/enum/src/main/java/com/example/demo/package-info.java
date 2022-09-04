package com.example.demo;


/**
 *
 *
 *  Mapping
 *
 *
 *        OneToOne
 *
 *
 *
 * @OneToOne
 *
 *
 *
 * @OneToMany and @ManyToOne
 *
 *
 *          One to Many and Many to one
 *
 *
 *          Chegg
 *
 *           *      User to book
 *  *      any number of books
 *  *
 *  *      Book ---- user
 *  *      1 --->- 1
 *  *      N ---<-- 1
 *  *
 *
 *
 *
 *  	 *          Book ---- Author
 * 	 *          1 --------> 1 Author
 * 	 *          N<--------- 1Author
 * 	 *          -----------------
 *
 *
 *
 *
 *
 *          Book to user
 *
 *          Book to User            -----------
 *
 *          1 unique book  ---- one user
 *
 *
 *
 *
 *
 *              Many books     <-------   one user
 *
 *
 *
 *          Book to User
 *
 *
 *          BookEntity                      UserEntity
 *
 *
 *
 *          @Many(Books)ToOne(User)                      @One(User)ToMany(Books)
 *
 *
 *
 *
 *          how should we describe it
 *
 *          Hibernate Entity Mapping
 *          (Book Entity) - a)
 *
 *          ---> Uni Directional
 *          --> Bi Directional
 *
 *
 * @Getter
 * @Setter
 *
 *          @Entity(Books)                               @Entity(User)
 *           @ManyToOne                                 @OneToMany (mappedBy = "orderByUser")
 *          @JoinColumn
 *          User orderByUser;                           List<Books> orderedBooks;
 *
 *                                                     fetching other columns of Books entity
 *
 *
 *
 *                                                     FetchType (Lazy)
 *                                                      getOrderedBooks()
 *
 *          Database
 *          - (b1 , n1 , a1 , u1)                       - ((u1, n1 , e1 )
 *          - (b2 , n2 , a2 , u1)                       - (u2, n2 , e2 )
 *          - (b3 , n3 , a3, u2)                        - (u3, n3 , e3)
 *
 *
 *                  -- foreign --- (primary key of (user) another table, used in book table)
 *
 *
 *
 *          User Entity -- b)
 *          - (u1, n1 , e1 , {b1 , b2....})
 *          - (u2, n2 , e2 , {b3,b4,...})
 *          - (u3, n3 , e3)
 *          - (u4, n4 , e4)
 *
 *              fetch --- ? Type
 *
 *          By default load type is --- of the mapping  fetchType = EAGER , LAZY (n+1) -- problems
 *           ---->   find all the books issued to a user
 * @Service
 *
 * public List<Books> fetchAllBooksByUser(long userId){
 *
 *
 * }
 *
 *
 * @Repository
 * public interface BookRepository extends JPARepos<Book><BookID/> {
 *
 *      @Query ("select * from books where userId = :alpha", native=true)
 *      public List<Books> fetchBooksByUser(@Param("alpha") Long userId)
 *
 *
 * }
 *
 *
 *
 *
 *      Composite key
 *          --> combination of two columns - which uniquely help us
 *
 *      Composite primary key
 *
 *          ----------------
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
 * @ManyToMany
 *
 *
 *      Caching
 *          - JVM Cache
 *          - Redis Cache
 *
 *
 *
 *     Caching
 *              -------->
 *
 *
 *        Tiny Url
 *              -------->
 *         tiny url
 *                      ---> big url made short
 *                      twitter ---> 140 characters blocking
 *
 *         Design
 *              --------
 *
 *              TIny url as service
 *                              ------------
 *                              DB schema design
 *                              {
 *                                      "id" -- primary key -
 *                                      "small url" - unique - length of the url - 5 characters
 *                                      "big url" - (small text)
 *                                      "created_at" =
 *                                      "updated_at" =
 *                              }
 *
 *
 *           basis on assumption
 *           that we can keep data for 1 year
 *
 *
 *           --> in terms of writes
 *
 *                  how many transactions in an hour -- ?
 *                  10k transactions per hour..  (5) -- 50k request in hour writes
 *
 *                  in 1 day how many hours -- 24
 *        in 1 day          50 k request in 1 hour -- > how many request 24 * 50 k = 1200 k
 *                365 days ---> inserts ? = 1200 * 365 k = 438 000 000  inserts in 1 years
 *
 *
 *
 *
 *                1 character can have how many values
 *                [0-9 , a-z , A-Z] = combinations
 *                  [26, 26, 10] = 62
 *
 *
 *                  2 characters how many combinations = (62) * (62)
 *
 *
 *
 *                  438 000 000  inserts -----> how many characters
 *
 *                  5 times
 *
 *
 *                  write to read ---- what ratio it would be
 *                      (1 : 100) --> fault toulerant -- ie burst --- 5
 *                  `1 write : 500 reads
 *
 *
 *
 *              TPS - transactions per second
 *
 *              50 k request in 1 hour (write)
 *              read = 100 times of writes =  50 * 100 k = 5 000 000  in 1 hours
 *
 *
 *              read + write = entire traffic =  50 k + 5000 k ~~~~~  5000 k
 *              1 hour -- 3600 seconds
 *
 *              1 second -- transactions = 1389 --- 1400 tps
 *
 *
 *              --> now
 *
 *                          tiny url
 *                       Fe ----> application -----> Db
 *                                                  -- IO less expensive if from memory than from Disk
 *
 *                                                  (million of records)
 *
 *                                                  how can you reduce the latency
 *
 *                                                  Vertical                                            vs Horizontal Scale
 *                                                  (increase ram / ssd / hardware resource)                (adding more machines)
 *
 *
 *                 latency ?? -- time required for processing a request
 *
 *
 *                          Caching it
 *
 *                  --> how does request generally goes through
 *
 *       -- Fe -----> akamai (may or may not) ------>   api gateway (may or may not) -----> load balancer
 *                      (throttling)           ----->
 *
 *
 *
 *                          LB  --------    SB1 (jvm cache) ----->   DB (master)        -- DB (slave)      (may or may not)
 *                                                             /  /
 *                                          SB2         -----/  /
 *                                                            /
 *                                          SB3         ----/
 *
 *
 *
 *
 *                  HashMap = new HashMap<>()
 *                      HW  -- what ths intial capacity when hashmap in initialized  ?
 *                          what the load factor -- ?
 *                          what happends when we insert data into hashmap beyond load factor - ?
 *                          16 bytes === >
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

