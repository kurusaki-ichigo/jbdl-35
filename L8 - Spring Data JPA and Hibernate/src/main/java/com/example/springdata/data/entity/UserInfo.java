package com.example.springdata.data.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Builder
public class UserInfo {

    /**
     * create table user_info (
     *        id bigint not null auto_increment,
     *         created_at datetime(6),
     *         name varchar(255),
     *         updated_at datetime(6),
     *         primary key (id)
     *     ) engine=InnoDB
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // provide own custom strategy...
    private UUID userIdentifier;

    private String name;


    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;


}
