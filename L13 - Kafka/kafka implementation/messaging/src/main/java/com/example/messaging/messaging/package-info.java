package com.example.messaging.messaging;


/**
 *
 *
 *
 *   we send message
 *                 from kafka
 *                          on a topic
 *
 *
 *
 *                                              value
 *                  kafka.sendMessage(topic , message);
 *                                             key     value
 *                  kafka.sendMessage(topic , userId , message);
 *
 *              Key --> what is key --->
 *
 *      user --->
 *
 *    5:55 pm          m1   you made a transaction of 100 dollars
 *    6:05 pm          m2   you made a transactions of 1000 dollars
 *
 *      at 6:10 -- m2 then you received m1
 *                                  (how would you feel) -- what would be experience -- m1 is received first then m2
 *        Ques                   want to perform transactions / things sequentially   -- for a particular user
 *                                          how would you do that ...
 *
 *                                          [one partition only ]
 *
 *
 *
 *             p1    [m2]
 *             p2    [dksdnsknfksfnsknfsknfksnfksnf]            only one consumer availble p1  and p2 and then would read from p3
 *             p3   [m1]
 *
 *
 *              based upon userId %3
 *
 *
 *
 *
 *
 */