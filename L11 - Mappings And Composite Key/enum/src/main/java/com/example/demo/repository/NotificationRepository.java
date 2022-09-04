package com.example.demo.repository;

import com.example.demo.entities.embeddable.NotificationId;
import com.example.demo.entities.embeddable.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notifications, NotificationId> {

    @Query("select  notification from Notifications notification where notification.notificationId.source = :sourceSerial")
    public List<Notifications> findAllBySource(@Param("sourceSerial") String source);


}
