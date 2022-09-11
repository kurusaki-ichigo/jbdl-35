package com.example.kafka.kafka.requests;

import com.example.kafka.kafka.entities.OrderStatus;
import com.example.kafka.kafka.entities.Orders;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class CreateOrderDto {

    Double amount;
    OrderStatus orderStatus;

    PaxInfo paxInfo;





    public Orders toOrder() {
        return Orders.builder()
                .identifier(UUID.randomUUID().toString())
                .amount(amount)
                .build();
    }





    @Data
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    public static class PaxInfo implements Serializable {
        Long id;
        String name;
        String email;
        String foodPreference;
        String identifier;
    }

}
