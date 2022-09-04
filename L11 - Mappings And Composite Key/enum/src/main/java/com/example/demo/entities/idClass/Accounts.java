package com.example.demo.entities.idClass;

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
@IdClass(AccountId.class)
public class Accounts {

    @Id
    String idempotencyKey;
    @Id
    String source;


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
