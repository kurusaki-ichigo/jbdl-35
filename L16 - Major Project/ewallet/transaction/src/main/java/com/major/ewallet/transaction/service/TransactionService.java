package com.major.ewallet.transaction.service;

import com.major.ewallet.transaction.entity.Transaction;
import com.major.ewallet.transaction.entity.TransactionStatus;
import com.major.ewallet.transaction.exception.TransactionNotFoundException;
import com.major.ewallet.transaction.model.NewWalletRequest;
import com.major.ewallet.transaction.model.TransientTransaction;
import com.major.ewallet.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
public class TransactionService {

    @Value("${pocketbook.user.topup}")
    Long topUpDefaultAmount;

    @Value("${pocketbook.user.systemId}")
    Long systemId;

    @Autowired
    TransactionRepository repository;

    public Transaction createNewPendingSystemTransaction(NewWalletRequest walletRequest){
        Transaction transaction = walletRequest.toTransaction();
        transaction.setAmount(Double.valueOf(topUpDefaultAmount));
        transaction.setSenderId(systemId);
        return saveOrUpdate(transaction);
    }

    private Transaction saveOrUpdate(Transaction transaction){
        return repository.save(transaction);
    }

    public Transaction markTransaction(TransientTransaction transientTransaction , TransactionStatus status){
        Optional<Transaction> byId = repository.findById(transientTransaction.getId());
        if(byId.isEmpty()){
            throw new TransactionNotFoundException();
        }

        Transaction persisted = byId.get();
        persisted.setStatus(status);
       return saveOrUpdate(persisted);
    }
}
