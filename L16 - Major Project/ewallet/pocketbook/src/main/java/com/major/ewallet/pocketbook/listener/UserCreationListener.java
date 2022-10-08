package com.major.ewallet.pocketbook.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.major.ewallet.pocketbook.entity.Wallet;
import com.major.ewallet.pocketbook.exception.WalletExistsException;
import com.major.ewallet.pocketbook.model.PocketBookUser;
import com.major.ewallet.pocketbook.service.WalletService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static com.major.ewallet.pocketbook.utils.KafkaMessageLogger.addCallBack;

@Component
@Slf4j
public class UserCreationListener {

    private static final String USER_CREATED = "USER_CREATED";

    private static final String NEW_WALLET_CREATED = "NEW_WALLET_CREATED";

    @Autowired
    ObjectMapper objectMapper;


    @Autowired
    WalletService service;


    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @SneakyThrows
    @KafkaListener(topics = {USER_CREATED}, groupId = "pocketbook_group")
    public void receivedMessage(@Payload String message){
        log.info(" *************** USER CREATED LISTENER :: start");
        /**
         *
         *
         */
        PocketBookUser user = objectMapper.readValue(message, PocketBookUser.class);
        try {
            Wallet newWallet = service.createNewWallet(user);
            /**
             * A
             */
            String messageOutbox = objectMapper.writeValueAsString(newWallet);
            addCallBack(messageOutbox , kafkaTemplate.send(NEW_WALLET_CREATED, messageOutbox));
            /**
             * B
             *
             * When to prefer B over A
             *
             */
//            kafkaTemplate.send(NEW_WALLET_CREATED, String.valueOf(user.getId()), objectMapper.writeValueAsString(newWallet));

        } catch (WalletExistsException exception){
            /**
             * do nothing as wallet exist;
             */
        }

        log.info(" *************** USER CREATED LISTENER :: end");


    }




}
