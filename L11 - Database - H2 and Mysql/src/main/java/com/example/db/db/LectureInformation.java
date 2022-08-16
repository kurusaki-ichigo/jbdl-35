package com.example.db.db;

public class LectureInformation {

    /**
     *
     *
     *      Google - UberEats free order Paytm - twitter thread -- do read around it
     *      https://twitter.com/gergelyorosz/status/1502947315279187979?lang=en
     *      importance of status code
     *
     *
     *
     *          Why its not --> ?? PUT method --> its changing the state of the instance over the server
     *
     *  CounterStrike
     *      (Shoot --> 1 shoot --> 3 kill -- per heath)
     *
     *      Put is not idempotent
     *      (shoot ---> 1 at time ) ---> request executed 3 times() ---> heath reduces by 3.
     *
     *     FE (IOS / ANDroid / Webapp - ) ----> api gateway --> akamai (rate limiting) --> load balancer
     *           load balancer   --> Application instance A1
     *           load balancer   --> A2         ---> Db (master) --- > Db (slave)
     *           load balancer   --> A3
     *
     *
     *
     *          (whenever -- there is put operation going on )
     *                      -- for every request (object id and a request id)
     *
     *                          // add distributed lock - redis, zookeeper
     *                          (service)   -- redis
     *                                   // hashmap             (requestId, user_who_has_pistol, request)
     *
     *
     *          application
     *
     *          CS object
     *          {                                               -----> Application instance (if redis / distributed hashmap has value)
     *              user_who_has_pistol : 3                             do not process the request
     *              user_who_has_been_shot : 5                  -- else process it
     *              bullets : 1
     *              requestId = 1234567
     *          }
     *
     *          {
     *              user_who_has_pistol : 3
     *              user_who_has_been_shot : 5
     *              bullets : 1
     *              requestId = 1234567
     *          }
     *
     *          {
     *              user_who_has_pistol : 3
     *              user_who_has_been_shot : 5
     *              bullets : 1
     *              requestId = 1234567
     *          }
     *
     *
     *
     *
     *
     *
     *
     *
     *          Queue
     *
     *
     *
     *          Db master -- DB slave
     *
     *          DB (master) <-----> DB (slave) --> ideally having the same data
     *
     *          (application --> we write ) --> we write to master
     *          (application we read) --> we read through slave
     *
     *
     *
     *          Master and Slave sync
     *          (CDC pipeline --
     *          Change data capture --- ?
     *
     *
     *          application
     *                  logs (agree / disaggree)
     *
     *
     *          MySQL               --> insert ()           | agent is installed  | (Maxwell | Debezium) |  Queue(insert () )
     *              (bin logs)      --insert ()             |
     *
     *          Postgres                                    |
     *              (waLL logs) ---- insert ()              |
     *
     *           Mongo
     *              (op logs) ----insert ()                 |
     *
     *
     *                                                                          (kafka consumer/ application)
     *
     *          MySQL               --> insert 1, 2, 3, 4, 5()           | |  Queue(insert () 1, 2, 3, 4, 5) |
     *              (bin logs)      --insert ()             |
     *
     *
     *           Mysql DB2(1, 2, 3, 4, 5)
     *
     *
     *
     *          DR -- disaster recovery
     *         laptop crashed  (own laptop --- > installed mysql -- master(3306) , slave(3370) )
     *
     *
     *          NO ..
     *          fire in the aws storage (aws instance in a regions (us-east-1 - north virginia) --- (instance of master and slave))
     *          deploy the services / db in the other region too --> disaster recovery
     *
     *
     *
     *
     *
     *
     *      (Booking.com) --->
     *          (Order microservice)        (order is success)                       (notification microservice)
     *
     *
     *          (order success) --> notification can read from it
     *
     * @param args
     */
    public static void main(String[] args) {

    }
}
