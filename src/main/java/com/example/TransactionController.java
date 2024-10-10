package com.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/types/{type}")
    public ResponseEntity<List<Long>> getTransactionsByType(@PathVariable String type){
        return ResponseEntity.ok(transactionService.getTransactionsByType(type));
    }

    @GetMapping("/sum/{transaction_id}")
    public ResponseEntity<Map<String, Double>> getTotalAmount(@PathVariable long transaction_id){
        double total = transactionService.getTotalAmount(transaction_id);
        Map<String, Double> sumMap = new HashMap<>();
        sumMap.put("sum", total);
        return ResponseEntity.ok(sumMap);
    }
}
