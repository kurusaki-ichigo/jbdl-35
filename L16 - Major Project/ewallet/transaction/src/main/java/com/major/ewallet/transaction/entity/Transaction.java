package com.major.ewallet.transaction.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
@JsonIgnoreProperties(value = {"status", "createdAt", "updatedAt"})
public class Transaction {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private OffsetDateTime createdAt;
    @UpdateTimestamp
    private OffsetDateTime updatedAt;



    @Enumerated(value = EnumType.STRING)
    private TransactionStatus status;

    private Double amount;

    private Long senderId;
    private Long receiverId;


    @PrePersist
    public void defaultTransactionUpdate(){
        this.status = TransactionStatus.PENDING;
    }


}
