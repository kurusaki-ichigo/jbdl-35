package com.example.kafka.kafka;

/**
 *
 *
 *  Kafka or message queue
 *          -->
 *
 *
 * Kafka (inhouse LinkedIN ) -- enterprize version maintained by confluent
 *
 *
 *
 *      IRCTC - (yes)
 *
 *
 *          -- hasnt heard about it..
 *
 *     Train booking -- IRCTC
 *      Design
 *          (Hint -- its using kafka for parallel processing and injection)
 *
 *     (as soon as you book -- the seat count decreases and a user can only buy / purchase a new ticket if the seat count > 0)
 *
 *      what would be the entities / services
 *
 *
 *      Entities
 *      flow - user would have (to station , from station)  + Date (normal booking)
 *
 *                  (Search)
 *              --- List of trains
 *                      ---> per train (there would be availability)
 *                                              ----> click on the availability
 *                                                      ----> re directed to booking details
 *
 *
 *             On Booking Details
 *                      ---> you need to add pax info , passenger prefences
 *                              --> Book --> Payment Details
 *
 *             On Payment Details
 *                      ---> you choose FOP.
 *                                      --> Credit / DEbit
 *                                                  --- Amount would be deducted
 *                                                          --> ticket confirmation
 *
 *             On Ticket Confirmation
 *                  -- your ticket number would be displayed ()
 *                                  --- your other relevant informatop
 *
 *
 *           Entities                                                                   Microservices
 *              User                                                                    Search
 *              trains + route                                                        Booking / Order ()
 *              Order
 *              Payment                                                                Payment
 *                                                                                     Notification (Email notification)
 *                                                                                     User service
 *                                                                               (authentication and authorization)
 *
 *
 *
 *       Ensure strong correlation between the no of seats availability
 *                  with        the parallel bookings taking place
 *
 *
 *
 *      as the booking would be success
 *                          ----> then you would have to reduce the seat count by 1
 *
 *
 *          Yes !!!
 *      Search --  {
 *              [{      (Train Information)
 *                      source:
 *                      destination:
 *                      Seats Available :
 *                      Time :
 *                      Price Information :
 *              },{}]
 *      }
 *
 *      flow of booking (Service Interaction)
 *
 *          Zuul (Proxy / Routing service)
 *          Eureka (Service Discovery)
 *          Ribbon (load balancer)
 *
 *
 *
 *      FE --> (Eureka Zuul Ribbon -- Netflix OSS)
 *         -->
 *
 *
 *             --> v1/book                                             User Service                     3rd Party
 *              (Book Service / Order Service)                   (app)  (pax information)
 *                          -----------  rest call  --  (update passenger information)->
 *                      ---- Order  ----------------(redirect url)--------------------------------------
 *                      (3rd party url, r)
 *
 *
 *      API
 *      (independent)  --> -- POST API : callback/?orderId={ORDERID}&status={orderStatus}
 *              (Order / bookservice)
 *                      -- POST API --- order success
 *                      you would redirect
 *
 *
 *
 *      API
 *      Payment Confirmation Page <hidden></>
 *                  (kindly wait do not click on cancel ---(popup))
 *    ---> internally polling backend         ----> whats the order status ? (terminal state - success / failure / PENDING_FAILURE)
 *    -->>>>>>>>>>>>>Order service (give me the response of payment confirmation)
 *
 *                          ---->---------------------------------> (user info)
 *                          -------------------------------------------------------------------------------------------(Search Service)
 *
 *
 *
 *
 *                                (unhide it and populate with other details)
 *
 *
 *             On Booking Details
 *                      ---> you need to add pax info , passenger prefences
 *                              --> Book --> Payment Details
 *
 *             On Payment Details
 *                      ---> you choose FOP.
 *                                      --> Credit / DEbit
 *                                                  --- Amount would be deducted
 *                                                          --> ticket confirmation
 *
 *             On Ticket Confirmation
 *                  -- your ticket number would be displayed ()
 *                                  --- your other relevant informatop
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