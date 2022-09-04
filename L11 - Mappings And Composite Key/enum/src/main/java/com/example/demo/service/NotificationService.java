package com.example.demo.service;

import com.example.demo.entities.embeddable.Notifications;
import com.example.demo.repository.NotificationRepository;
import com.example.demo.request.CreateNotificationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class NotificationService {


    @Autowired
    NotificationRepository repository;


    public Notifications persistNotification(CreateNotificationDto createNotificationDto){
        Notifications notifications = createNotificationDto.toNotifications();
        Optional<Notifications> byId = repository.findById(notifications.getNotificationId());
        if(byId.isPresent()){
            throw new RuntimeException();
        }
        return saveOrUpdate(notifications);
    }


    private Notifications saveOrUpdate(Notifications notifications){
        return repository.save(notifications);
    }
}
