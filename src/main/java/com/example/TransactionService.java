package com.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private Map<Long, Transaction> transactionsMap = new HashMap<>();
    
    public void addTransaction(long id, Transaction transaction){
        transaction.setId(id);
        transactionsMap.put(transaction.getId(), transaction);
    }

    public List<Long> getTransactionsByType(String type) {
        return transactionsMap.values().stream()
                .filter(t -> t.getType().equals(type))
                .map(Transaction::getId)
                .collect(Collectors.toList());
    }
}
