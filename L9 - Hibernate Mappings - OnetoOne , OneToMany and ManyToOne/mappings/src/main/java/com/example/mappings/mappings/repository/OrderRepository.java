package com.example.mappings.mappings.repository;

import com.example.mappings.mappings.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {
}
