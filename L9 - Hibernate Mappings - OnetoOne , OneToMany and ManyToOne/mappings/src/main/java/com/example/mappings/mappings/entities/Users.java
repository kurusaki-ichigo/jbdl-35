package com.example.mappings.mappings.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@Table( name = "user_info" , indexes = {@Index(name = "uniqueEmail", columnList = "email_id" , unique = true)})
public class Users {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long userId;

    String name;

    @Column(name = "email_id", columnDefinition = "varchar(50)")
    String email;

    @CreationTimestamp
    LocalDateTime createdAt;

    @UpdateTimestamp
    LocalDateTime updatedAt;


    @OneToMany(mappedBy = "user")
    @JsonIgnore
    @ToString.Exclude
    List<Orders> userOrders;

}
