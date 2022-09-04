package com.example.demo.entities.idClass;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;


@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AccountId implements Serializable {
    private static final long serialVersionUID = 5706988877334359930L;
    String idempotencyKey;
    String source;
}
