package com.example.kafka.kafka.entities;


import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String identifier;
    String userIdentifier;
    Double amount;
    @Enumerated(value = EnumType.STRING)
    OrderStatus orderStatus;




    @PrePersist
    public void prePersist(){
        orderStatus = OrderStatus.PENDING;
    }


}
