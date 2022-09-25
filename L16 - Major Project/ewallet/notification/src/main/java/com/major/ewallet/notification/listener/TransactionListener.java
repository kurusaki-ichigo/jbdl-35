package com.major.ewallet.notification.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.major.ewallet.notification.models.NotificationUser;
import com.major.ewallet.notification.models.Transaction;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Component
@Slf4j
public class TransactionListener {

    private static final String TRANSACTION_SUCCESS = "TRANSACTION_SUCCESS";

    private static final String TRANSACTION_FAILURE = "TRANSACTION_FAILURE";


    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    Gson gson;

    @Value("${pocketbook.user.systemId}")
    Long systemId;

    @Value("${spring.mail.username}")
    String systemUser;



    @SneakyThrows
    @KafkaListener(topics = {TRANSACTION_SUCCESS}, groupId = "notification_group")
    public void sendSuccessMessage(String message){
        /**
         * sender Id -- do not have email
         * receiver Id -- do not have email
         */

        Transaction transaction = objectMapper.readValue(message, Transaction.class);

        /**
         * fetch a Sender User
         */



        NotificationUser sender = fetchUserById(transaction.getSenderId());
        NotificationUser receiver = fetchUserById(transaction.getReceiverId());

        if(!Objects.equals(sender.getId(), systemId)){
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(systemUser);
            simpleMailMessage.setTo(sender.getEmail());
            simpleMailMessage.setText(" Hey ! " + sender.getName() + " your account has been debited by " + transaction.getAmount());
            simpleMailMessage.setSubject("Amount Debited");
            javaMailSender.send(simpleMailMessage);
        }

        if(!Objects.equals(receiver.getId(), systemId)){
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(systemUser);
            simpleMailMessage.setTo(receiver.getEmail());
            simpleMailMessage.setText(" Hey ! " + receiver.getName() + " your account has been credited by " + transaction.getAmount());
            simpleMailMessage.setSubject("Amount Credited");
            javaMailSender.send(simpleMailMessage);
        }
    }




    @SneakyThrows
    @KafkaListener(topics = {TRANSACTION_FAILURE}, groupId = "notification_group")
    public void sendFailureMessage(String message) {
        Transaction transaction = objectMapper.readValue(message, Transaction.class);
        NotificationUser sender = fetchUserById(transaction.getSenderId());
        if(!Objects.equals(sender.getId(), systemId)){
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(systemUser);
            simpleMailMessage.setTo(sender.getEmail());
            simpleMailMessage.setText(" Hey ! " + sender.getName() + " your recent transaction for " + transaction.getAmount() + " has failed !");
            simpleMailMessage.setSubject("Transaction Failed");
            javaMailSender.send(simpleMailMessage);
        }
    }






        private NotificationUser fetchUserById(Long id) {
        if(Objects.equals(id, systemId)){
            NotificationUser sender = new NotificationUser();
            sender.setId(systemId);
            return sender;
        }


        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:9095/user/" + id, String.class);
       return gson.fromJson(forEntity.getBody(), NotificationUser.class);
    }


}
