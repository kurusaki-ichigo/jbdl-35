package com.example.demo.repository;

import com.example.demo.entities.embeddable.NotificationId;
import com.example.demo.entities.embeddable.Notifications;
import com.example.demo.entities.idClass.AccountId;
import com.example.demo.entities.idClass.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, AccountId> {


    public List<Accounts> findAllBySource(String source);
}
