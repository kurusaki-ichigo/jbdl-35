package com.major.ewallet.notification.models;

import lombok.Data;

@Data
public class Transaction {

    Long senderId;
    Long receiverId;
    Double amount;
    Long id;

}
