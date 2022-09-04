package com.example.demo.entities.embeddable;


import com.example.demo.enums.ChannelType;
import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@TypeDef(name = "json", typeClass = JsonType.class)
public class Notifications {


    @EmbeddedId
    NotificationId notificationId;

    String channelValue;
    /**
     * Type as String not as Ordinal
     */
    @Enumerated(value = EnumType.STRING)
    ChannelType channelType;

    String templateId;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    Map<String , String> dynamicFields;


    @CreationTimestamp
    LocalDateTime createdAt;

    @UpdateTimestamp
    LocalDateTime updatedAt;



}
