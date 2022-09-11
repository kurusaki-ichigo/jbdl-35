package com.example.messaging.messaging.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
@Slf4j
public class SampleProducer implements InitializingBean {


    @Autowired
    KafkaTemplate<String , String> kafkaTemplate;

    @Value("${app.kafka.topic}")
    String topicName;

    private static final String MESSAGE = "Hey ! I am doing just fine before I met you ";

    @Override
    public void afterPropertiesSet() {
        log.info(" send message ");
        ListenableFuture<SendResult<String, String>> send = kafkaTemplate.send(topicName, MESSAGE);
        addCallBack(MESSAGE, send);
    }

    public void addCallBack(String message ,  ListenableFuture<SendResult<String, String>> send){
        send.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.info(" ***** Unable to send message=[" + message + "]");
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info(" ***** Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
        });
    }




}
