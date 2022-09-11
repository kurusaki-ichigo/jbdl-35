package com.example.user.user.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
public class UserInfo {

    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String identifier;
    String name;
    String email;
    FoodPreference foodPreference;

    /**
     @Data
     public static class PaxInfo{
     String name;
     String email;
     String foodPreference;
     }

     */
}
