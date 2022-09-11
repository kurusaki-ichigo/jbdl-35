package com.example.kafka.kafka.example;


/**
 *
 *
 *                                  Zookeeper (keep state of the kafka brokers / cluster)
 *
 *
 *                                  (Kafka - Distributed)
 *      Order service   -----------> Queue          <--------- User Service
 *      (Producer) ---> 1 st senario
 *
 *                                     (Broker)                     (consumer)
 *
 *
 *                  How many broker node ?
 *                                          they would be in odd number
 *                                          List<Brokers>
 *
 *                                              Leader</>
 *
 *                                          </Broker>  </Broker>
 *
 *         certain properties of kafka
 *          - bootstarp url : url of the kafka brokers
 *                              localhost:9092,
 *
 *      producer
 *              -- acknowledgement
 *              -1
 *              1
 *              0
 *
 *
 *
 *
 *
 *
 *
 *      consumer
 *              --- consumer group
 *
 *                              partitions -- no of parallel queues
 *
 *                              *** NOTE : leader / follower is among partition
 *
 *
 *                                                           ---> consumer_group ie if the consumer group id are different
 *                                                                ------  INSIDE ONE BROKER -------
 *
 *
 *                                                                  P0      [4, 1]       per partition there would be at max one goon from RAT , WALVE
 *          Producer               CHECK_AND_CREATE_USER ----->     P1      [5, 2]
 *        [1,2 ,3, 4, 5, 6]                                         P2      [6, 3]
 *
 *
 *                                                                                              person4
 *      // ordering of messages
 *
 *              2 group of goons    (g1 - RAT)   ---- {person1, person2, person3, person4}
 *                                  (g2 --- WALVE) - {boy1, boy2, boy3}
 *      some gold -->
 *
 *
 *      1st Scenario
 *          --> order service ----publishing-> ( CHECK_AND_CREATE_USER, {paxInfo})
 *                                                              user service ----> consuming message from (CHECK_AND_CREATE_USER , perssiting if appl. paxInfo )
 *
 *
 *
 *
 *
 *
 *  b0
 *  CHECK_AND_CREATE_USER
 *        P0      [4, 1]     leader
 *        P1      [5, 2]     follower
 *        P2      [6, 3] follwer
 *


 *  b1
 *  CHECK_AND_CREATE_USER
 *        P0      [4, 1]       follwer
 *        P1      [5, 2]  follwer
 *        P2      [6, 3]    leader


 *  b2
 *  CHECK_AND_CREATE_USER
 *        P0      [4, 1]    follwer
 *        P1      [5, 2]    leader
 *        P2      [6, 3]    follwer



 *
 */