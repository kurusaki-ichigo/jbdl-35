package com.major.ewallet.pocketbook.model;

import com.major.ewallet.pocketbook.entity.Wallet;
import lombok.Data;

@Data
public class PocketBookUser {

    private Long id;

    private String email;
    private String name;

    public Wallet toWallet(){
        return Wallet.builder()
                .userId(id)
                .balance((double) 0)
                .build();
    }

}
