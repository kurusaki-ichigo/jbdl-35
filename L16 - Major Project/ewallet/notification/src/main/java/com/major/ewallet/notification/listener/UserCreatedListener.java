package com.major.ewallet.notification.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.major.ewallet.notification.models.NotificationUser;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserCreatedListener {

    private static final String USER_CREATED = "USER_CREATED";

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    String systemUser;

    @SneakyThrows
    @KafkaListener(topics = {USER_CREATED}, groupId = "notification_group")
    public void receivedMessage(@Payload String message){
        /**
         * send a message to a user his account has been created
         *
         *
         */

        log.info("************************ SENDING EMAIL TO NEW USER CREATION : START ************************");


        NotificationUser notificationUser = objectMapper.readValue(message, NotificationUser.class);

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(notificationUser.getEmail());
        simpleMailMessage.setFrom(systemUser);
        simpleMailMessage.setSubject("Welcome Onboard !");
        simpleMailMessage.setText(" Hey ! " + notificationUser.getName() + " your account has been successfully created with us !" +
                "" +
                "" +
                " We will be crediting some balance in a short time as a good gesture for enrolling in our services. Thanks !");
        javaMailSender.send(simpleMailMessage);
        log.info("************************ SENDING EMAIL TO NEW USER CREATION : END  ************************");
    }





}
