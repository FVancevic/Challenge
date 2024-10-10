package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PutMapping("/{transaction_id}")
    public ResponseEntity<String> addTransaction(@PathVariable long transaction_id, @RequestBody Transaction transaction){
        transactionService.addTransaction(transaction_id, transaction);
        return ResponseEntity.ok("{\"status\": \"ok\"}");
    }

    
}
