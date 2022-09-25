package com.major.ewallet.transaction.model;

import com.major.ewallet.transaction.entity.Transaction;
import lombok.Data;

@Data
public class NewWalletRequest {

    Long id;
    Long userId;

    public Transaction toTransaction(){
        return Transaction.builder()
                .receiverId(userId)
                .build();
    }
}
