package com.example.payment.payment.repository;

import com.example.payment.payment.entity.JWTClient;
import com.example.payment.payment.entity.S2SCleint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JwtRepository extends JpaRepository<JWTClient, Long> {


    Optional<JWTClient> findByClient(S2SCleint client);
}
