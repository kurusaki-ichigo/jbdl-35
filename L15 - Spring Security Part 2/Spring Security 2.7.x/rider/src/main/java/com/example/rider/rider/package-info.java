package com.example.rider.rider;

/**
 *
 *
 * MICROSERVICES INVOLVED
 *  (UBER)
 *
 *
 *  --> USER MICRO
 *
 *  --> BOOK
 *
 *  ---> PAYMENT
 *
 *
 *  --> INVOICE ()
 *
 *
 *  --> RECONCILIATION
 *
 *  --> NOTIFICATION MANAGER
 *
 *
 *                                                                        PAYMENT(CLIENT has role)
 *                          NOTIFICATION MANAGER-----X X X-------->
 *
 *          INVOICE ------>
 *                  -------> BOOK
 *                  ------> USER
 *
 *
 *          S2S (service to service)
 *                              would be authenticated with JWT token
 *
 *
 *
 */