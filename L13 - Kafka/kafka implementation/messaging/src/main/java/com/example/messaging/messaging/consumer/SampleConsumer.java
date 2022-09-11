package com.example.messaging.messaging.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SampleConsumer {


    @Autowired
    JavaMailSender javaMailSender;


    @KafkaListener(topics = {"${app.kafka.topic}"}, groupId = "group_ID")
    public void processMessage(@Payload String message){
        log.info(" *************************** received message {} ", message);

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setText(message);
        simpleMailMessage.setSubject("Sending Hi...");
        simpleMailMessage.setFrom("pmiglani1994@gmail.com");
        simpleMailMessage.setTo("kress89@gmail.com");
        javaMailSender.send(simpleMailMessage);

    }

}
