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
        log.info("************* INSIDE TRANSACTION_SUCCESS : start ");
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
            log.info("************* INSIDE TRANSACTION_SUCCESS : SENDING MESSAGE TO SENDER ");
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(systemUser);
            simpleMailMessage.setTo(sender.getEmail());
            simpleMailMessage.setText(" Hey ! " + sender.getName() + " your account has been debited by " + transaction.getAmount());
            simpleMailMessage.setSubject("Amount Debited");
            javaMailSender.send(simpleMailMessage);
        }

        if(!Objects.equals(receiver.getId(), systemId)){
            log.info("************* INSIDE TRANSACTION_SUCCESS : SENDING MESSAGE TO RECEIVER ");
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(systemUser);
            simpleMailMessage.setTo(receiver.getEmail());
            simpleMailMessage.setText(" Hey ! " + receiver.getName() + " your account has been credited by " + transaction.getAmount());
            simpleMailMessage.setSubject("Amount Credited");
            javaMailSender.send(simpleMailMessage);
        }
        log.info("************* INSIDE TRANSACTION_SUCCESS : end ");
    }




    @SneakyThrows
    @KafkaListener(topics = {TRANSACTION_FAILURE}, groupId = "notification_group")
    public void sendFailureMessage(String message) {
        log.info("************* INSIDE TRANSACTION_FAILURE : SENDING MESSAGE TO SENDER ");
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


    /**
     *
     *
     *
     * topic_offset - topic created by kafka
     *      [what partition and offset has been read , per consumer group]
     *          p1 - 2 resume
     *
     *
     * 0 -10 are
     *
     *        3     2     1    0
     * [p1] [M6] [M3] [M2] [M1]       - one consumer per partition per (consumer group)
     *
     *
     *         2   1      0
     * [p2] [M5] [M4] [M3]
     *
     *
     *         2   1   0
     * [p3] [M8] [M7] [M9]
     *
     *
     *          kafka producer --> (topic)
     *
     *
     */


    /**
     *
     *
     *     Group : POCKET_BOOK      (always keep no of consumers = no of partitions + 1) -- Pocket_1 , Pocket_2, Pocket_3 (idle)
     *     Group : Notification     (Notification_1 , Notification_2, Notification_3 ((idle)))
     *
     *      (UserService)   --> (USER_CREATED , m3) -------> kafka broker
     *                                                      topics partitions
     *
     *
     *                                                       USER_CREATED
     *                                                              1        0
     *                                                       p[0]  m2  ,    m0
     *
     *
     *                                                             1        0
     *                                                        p[1]  m3  ,   m1
     *
     *
     *                                                       USER_CREATED_OFFSET_COMMITTED
     *
     *                                                       p[0] , offset -0 -> POCKET_BOOK
     *
     *
     *
     *
     *      Pocket_1 - assigned partition 1
     *      Pocket_2 - assigned partition 0 - starts from m2
     *
     *      Notification_1 - assigned 0
     *      Notification_2 - assigned 1
     *
     *
     *      sending message - topic , message ---> now send a message for notification
     *                                              ---> 2 listeners - pocket  , notification
     *
     *                          topic , -- key -- ? , value (message)
     *
     *                                     userId       2 ------ - partition 3 - in sequnetial order
     *
     *
     *                               property to be used along with it - max in flights requests = 1
     *
     *                              sequential A , B, C, D
     *
     *
     */




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
