package com.example.ehcache.ehcache;


/**
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
 *         Tiny url --> why ? -->
 *          because of restriction
 *           {
 *  *                                      "id" -- primary key -
 *  *                                      "small url" - unique - length of the url - 5 characters
 *  *                                      "big url" - (small text)
 *  *                                      "created_at" =
 *  *                                      "updated_at" =
 *  *        }
 *
 *
 *          why do we need to cache -- ?
 *                      ---> for performance
 *
 *
 *                              a user inserted data --> created a tiny url  t
 *
 *                              when do you think that there would be more reads of the url
 *
 *
 *                              at time {t, t+ 2 days} or beyond that -- ? {}
 *
 *                              how much time does a video on youtube take to reach from
 *
 *                              1 million to 2 millions  --- would reach faster
 *                              vs 0 to 1 millions -- would reach faster
 *
 *                              why ? -- ? youtube recommendation
 *                                          --->  1 million to 2 millions
 *                      A week
 *                                  can it remains viral beyond a week --> it can be -- not neccessarily
 *
 *
 *                                  hot links - hot urls (we need to cache these urls)
 *                                  what should be duration of likeliness of cache
 *
 *                                  Cache Hit -- {
 *                                      if the data is found in Cache
 *                                  }
 *
 *                                  Cache Miss -- {
 *                                      if the data is not found in Cache
 *                                  }
 *
 *                                  -- to derive what should be ttl --> we derive logic around cache miss
 *
 *
 *                                  Cache is <--></--> ?
 *                                  store house of key value pairs
 *
 *
 *          JVM cache
 *          how does the request flow in application
 *                      LB  --------    SB1 (jvm cache) ----->   DB (master)        -- DB (slave)      (may or may not)
 *  *                                                             /  /
 *  *                                          SB2         -----/  /
 *  *                                                            /
 *  *                                          SB3         ----/
 *  *
 *  *
 *                  How does load balancer behaves --?
 *
 *  *
 *                    LB  pick which server to send the request
 *                      (various algorithms)
 *                      -> ROUND ROBIN (-- Doing a task for a time ---)
 *                      -> Based on health of instance
 *                      -> Based on request processing time
 *                      -> USERID hash (link Hash)
 *
 *                      -> CPU utilization metrics  (2-3 seconds)
 *
 *                      Auto-scaling is ?
 *
 *                      Yes -
 *                      No - 11
 *
 *                      various
 *                          ---> SB1 ()-- as soon as CPU Utilization > 60 or 70..
 *                          ---auto scaling done --> start another server
 *
 *
 *      Distributed
 *          (Redis)
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
 *
 *
 *
 */