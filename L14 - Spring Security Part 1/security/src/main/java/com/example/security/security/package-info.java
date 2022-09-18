package com.example.security.security;

/**
 *
 *
 * Spring security
 *
 *      (Zomato / Deliveroo / Bolt)
 *
 *
 *      discuss
 *      (
 *          Online food merchant aggregator and hyper delivery orgs.
 *      )
 *
 *      User --------------> Create order (adding a food item , and paying for that amount)
 *
 *      Merchant / Restaurant Manager
 *              --> would accept / reject the order
 *                          ---> receive a part of the payment
 *      Driver ----> food delivery agent
 *      Deliveroo / Zomato / Bolt
 *                  --> receiving a part of the payment that is being made by the user
 *
 *      Food Aggregator Admin - they are responsible for enlisting and delisting of a restaurant
 *
 *
 * @Secured("Customer") or @PreAuthorize("Customer")
 *      POST - (/order) {place an order}
 *      this api should be visible to -- everyone / customer only / driver only / merchant / combination
 *
 *      --> Customer and Merchant
 *      --> Driver  (wont be)
 *      --> Merchant place an order ? ()
 *      --> Customer
 *
 *
 *      POST - (/payment)
 * @Secured("Customer") or @PreAuthorize("Customer")
 *      this api should be visible to -- everyone / customer only / driver only / merchant / combination
 *        --> Customer
 *
 *
 * @Preauthorize("hasRole('Customer') OR hasRole('Merchant') or hasRole('Driver')")
 *        GET (/payment/)
 *      this api should be visible to -- everyone / customer only / driver only / merchant / combination
 *
 *      --> Driver ? Payment status (COD -- cash on delivery)
 *      --> Merchant ? Yes to check if merchant has received any payment (post order processing or at the time of reconcilation)
 *                  (one day reconciliation cycle , multi days) --- depends upon contract
 *      ---> Customer  ? (Payment)
 *
 *
 *
 *      Authentication
 *                      ---->
 *                              Whether user exists for a given application
 *
 *                              Exception - with status code - 401
 *
 *
 *
 *      Authorisation
 *                      ---->
 *                              whether the user is permitted to utilize a resource that is being requested
 *
 *                              Exception with status code - 403 (Forbidden)
 *
 *
 *
 *
 */