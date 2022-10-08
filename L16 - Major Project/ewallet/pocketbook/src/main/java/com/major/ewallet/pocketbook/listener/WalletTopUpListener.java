package com.major.ewallet.pocketbook.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.major.ewallet.pocketbook.exception.WalletExistsException;
import com.major.ewallet.pocketbook.model.PendingTransaction;
import com.major.ewallet.pocketbook.service.WalletService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import static com.major.ewallet.pocketbook.utils.KafkaMessageLogger.addCallBack;

@Component
@Slf4j
public class WalletTopUpListener {

    private static final String TOPUP_WALLET = "TOPUP_WALLET";

    private static final String TOPUP_SUCCESS = "TOPUP_SUCCESS";

    private static final String TOPUP_FAILURE = "TOPUP_FAILURE";

    @Autowired
    ObjectMapper objectMapper;


    @Autowired
    WalletService service;


    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;


    @SneakyThrows
    @KafkaListener(topics = {TOPUP_WALLET}, groupId = "pocketbook_group")
    public void receivedMessage(@Payload String message) {
        log.info("************** TOPUP_WALLET LISTENER : START ");
        /**
         *
         *
         */
        PendingTransaction transaction = objectMapper.readValue(message, PendingTransaction.class);
        String messageOutbox = null;
        ListenableFuture<SendResult<String, String>> send = null;
        try {
            service.topUpWallets(transaction);
            messageOutbox = objectMapper.writeValueAsString(transaction);
            send = kafkaTemplate.send(TOPUP_SUCCESS, objectMapper.writeValueAsString(transaction));
        } catch (WalletExistsException exception) {
            log.info("************** TOPUP_WALLET LISTENER : INSIDE CATCH EXCEPTION ");
            send = kafkaTemplate.send(TOPUP_FAILURE, objectMapper.writeValueAsString(transaction));
        } finally {
            log.info("************** TOPUP_WALLET LISTENER : INSIDE FINALLY ");
            assert send != null;
            addCallBack(messageOutbox, send);
        }
        log.info("************** TOPUP_WALLET LISTENER : END ");
    }

}
