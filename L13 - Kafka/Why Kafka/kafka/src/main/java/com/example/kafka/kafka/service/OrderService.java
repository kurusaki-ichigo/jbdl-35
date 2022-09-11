package com.example.kafka.kafka.service;

import com.example.kafka.kafka.entities.Orders;
import com.example.kafka.kafka.repository.OrderRepository;
import com.example.kafka.kafka.requests.CreateOrderDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ObjectMapper objectMapper;


    @SneakyThrows
    public Orders createOrder(CreateOrderDto createOrderDto){
        Orders orders = createOrderDto.toOrder();

        /**
         * save the user details
         *     String name;
         *     String email;
         *     String foodPreference;
         *
         */
        CreateOrderDto.PaxInfo paxInfo = createOrderDto.getPaxInfo();
        String forObject = restTemplate.postForObject("http://localhost:8081/user",paxInfo, String.class);
        log.info(" forObject {} ", forObject);
        CreateOrderDto.PaxInfo userInfo = objectMapper.readValue(forObject, CreateOrderDto.PaxInfo.class);
        log.info(" user {} ", userInfo);
        orders.setUserIdentifier(userInfo.getIdentifier());
        log.info(" order {} ", orders);
        return orderRepository.save(orders);
    }


    private Orders saveOrUpdate(Orders orders){
        return orderRepository.save(orders);
    }

}
