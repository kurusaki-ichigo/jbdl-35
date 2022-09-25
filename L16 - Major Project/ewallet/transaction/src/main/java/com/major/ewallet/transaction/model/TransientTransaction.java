package com.major.ewallet.transaction.model;

import lombok.Data;

@Data
public class TransientTransaction {
    Long senderId;
    Long receiverId;
    Double amount;
    Long id;
}
