package com.example;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private Map<Long, Transaction> transactionsMap = new HashMap<>();
    
    public void addTransaction(long id, Transaction transaction){
        transaction.setId(id);
        transactionsMap.put(transaction.getId(), transaction);
    }

}
