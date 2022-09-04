package com.example.demo.service;

import com.example.demo.entities.idClass.AccountId;
import com.example.demo.entities.idClass.Accounts;
import com.example.demo.repository.AccountsRepository;
import com.example.demo.request.CreateNotificationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AccountService {


    @Autowired
    AccountsRepository repository;


    public Accounts persistAccounts(CreateNotificationDto createNotificationDto){
        Accounts accounts = createNotificationDto.toAccounts();
        AccountId accountId = new AccountId(accounts.getIdempotencyKey(), accounts.getSource());
        Optional<Accounts> byId = repository.findById(accountId);
        if(byId.isPresent()){
            throw new RuntimeException();
        }
        return saveOrUpdate(accounts);
    }


    private Accounts saveOrUpdate(Accounts accounts){
        return repository.save(accounts);
    }
}
