package com.major.ewallet.pocketbook.service;

import com.major.ewallet.pocketbook.entity.Wallet;
import com.major.ewallet.pocketbook.exception.WalletExistsException;
import com.major.ewallet.pocketbook.model.PendingTransaction;
import com.major.ewallet.pocketbook.model.PocketBookUser;
import com.major.ewallet.pocketbook.repository.WalletRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class WalletService {

    @Autowired
    WalletRepository repository;

    @Value("${pocketbook.user.systemId}")
    Long systemId;


    public Wallet createNewWallet(PocketBookUser user){
        Optional<Wallet> byUserId = repository.findByUserId(user.getId());
        if(byUserId.isPresent()){
            throw new WalletExistsException();
        }
        return saveOrUpdate(user.toWallet());
    }

    private Wallet saveOrUpdate(Wallet wallet){
        return repository.save(wallet);
    }


    public void  topUpWallets(PendingTransaction pendingTransaction){
        /**
         * Sender wallet
         *      and check if there are sufficient funds
         *  Receiver wallet
         *
         *  reduce amount and done
         *
         */
        log.info(" received {} ", pendingTransaction);
        if(!Objects.equals(pendingTransaction.getSenderId(), systemId)) {
            Optional<Wallet> senderWallet = repository.findByUserId(pendingTransaction.getSenderId());
            if(senderWallet.isEmpty()){
                log.error(" Sender wallet is not present ");
                throw new RuntimeException();
            }
            if(senderWallet.get().getBalance() < pendingTransaction.getAmount() ){
                // insufficient balance
                log.error(" Sender wallet has insufficient balance ");
                throw new RuntimeException();
            }
            Wallet sender = senderWallet.get();
            sender.setBalance(Double.sum(sender.getBalance() , -1 * pendingTransaction.getAmount()));
            saveOrUpdate(sender);
        }


        if(!Objects.equals(pendingTransaction.getReceiverId(), systemId)) {
            Optional<Wallet> receiverWallet = repository.findByUserId(pendingTransaction.getReceiverId());
            if(receiverWallet.isEmpty()){
                log.error(" Receiver wallet is not present ");
                throw new RuntimeException();
            }
            Wallet receiver = receiverWallet.get();
            receiver.setBalance(Double.sum(receiver.getBalance() , pendingTransaction.getAmount()));
            saveOrUpdate(receiver);
        }



    }


}
