package com.example.mappings.mappings.enums;

public enum BookStatus {

    ISSUED  ,OUT_OF_STOCK, INVENTORY_REQUESTED ,AVAILABLE;


    public boolean isAvailable(){
        return this == AVAILABLE;
    }
}
