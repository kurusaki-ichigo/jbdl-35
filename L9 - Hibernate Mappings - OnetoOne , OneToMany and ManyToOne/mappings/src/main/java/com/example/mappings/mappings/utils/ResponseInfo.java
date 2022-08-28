package com.example.mappings.mappings.utils;

import com.example.mappings.mappings.enums.StatusCodes;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.MDC;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseInfo<T> {

    T data;
    String errorMessage;
    String errorCode;
    String traceId;

    /**
     *
     * FE  --- interact with backend application
     *  (response format changes for every reponse that you are returning )
     *
     *
     *      Microservice Architecture
     *
     *              Monolith - everything is in same application
     *                  single app doing (users , books , authors , order)
     *              vs
     *              Microservice
     *          Breaking monolith into different appication
     *          (book service , payment differnt , notification, orders )
     *
     *
     *
     *
     *          --> what would happen when order is placed
     *                  user (user) -- add a book to cart
     *                      --> click on pay
     *                          ---> payment would be done
     *                                  ---> order would be marked as per payment
     *                                              ---> (Success) -- Success message is shown to user
     *
     *       (User) 1
     *       (Order) 2
     *       (Payment) 3
     *       (Notification) 4
     *
     *
     *                  ------> 1 - interact with user to fetch the user profile
     *              (3) invoked -----> generate order (2) --> mark it pending
     *              (3) --- third party (to deduct balance)
     *              (3) is success ----> 2 (mark order success) ----> (4) send notification
     *
     *
     *
     *
     *              you want to identify where is the exception
     *              (traceId and spanId)
     *              traceId - is unique per request
     *                          --> spanId is unique per microservice
     *
     *               -- add spring sleuth as dependency -- and your logs would be fine
     *
     *
     *
     *
     *
     *
     *
     * @param statusCodes
     */



    public ResponseInfo(StatusCodes statusCodes){
        this.errorCode = statusCodes.getMessageCode();
        this.errorMessage = statusCodes.getMessage();
        this.traceId = MDC.get("traceId");
    }




}
