package com.major.ewallet.transaction.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.major.ewallet.transaction.entity.Transaction;
import com.major.ewallet.transaction.entity.TransactionStatus;
import com.major.ewallet.transaction.model.NewWalletRequest;
import com.major.ewallet.transaction.model.TransientTransaction;
import com.major.ewallet.transaction.service.TransactionService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TransactionProcessorListener {


    private static final String TOPUP_SUCCESS = "TOPUP_SUCCESS";

    private static final String TOPUP_FAILURE = "TOPUP_FAILURE";

    private static final String TRANSACTION_SUCCESS = "TRANSACTION_SUCCESS";

    private static final String TRANSACTION_FAILURE = "TRANSACTION_FAILURE";



    @Autowired
    TransactionService transactionService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    KafkaTemplate<String , String> kafkaTemplate;

    @SneakyThrows
    @KafkaListener(topics = {TOPUP_SUCCESS}, groupId = "transaction_group")
    public void processSuccessTransaction(@Payload String message){
        /**
         * create a transientTransaction in pending
         *          --- topup wallet
         *                      -- mark transientTransaction success
         */
        TransientTransaction transientTransaction = objectMapper.readValue(message, TransientTransaction.class);
        Transaction transaction = transactionService.markTransaction(transientTransaction, TransactionStatus.SUCCESS);
        kafkaTemplate.send(TRANSACTION_SUCCESS, objectMapper.writeValueAsString(transaction));
    }


    @SneakyThrows
    @KafkaListener(topics = {TOPUP_FAILURE}, groupId = "transaction_group")
    public void processFailedTransaction(@Payload String message){
        /**
         * create a transientTransaction in pending
         *          --- topup wallet
         *                      -- mark transientTransaction success
         */
        TransientTransaction transientTransaction = objectMapper.readValue(message, TransientTransaction.class);
        Transaction transaction = transactionService.markTransaction(transientTransaction, TransactionStatus.FAILURE);
        kafkaTemplate.send(TRANSACTION_FAILURE, objectMapper.writeValueAsString(transaction));
    }


}
