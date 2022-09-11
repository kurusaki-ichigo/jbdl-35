package com.example.kafka.kafka.repository;

import com.example.kafka.kafka.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Orders, Long> {

    Optional<Orders> findByIdentifier(String identifer);


}
