package com.dalencon.DesafioPicPay.controllers;

import com.dalencon.DesafioPicPay.dtos.TransactionDTO;
import com.dalencon.DesafioPicPay.services.TransactionService;
import com.dalencon.DesafioPicPay.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> creatTransaction(@RequestBody TransactionDTO transaction) throws Exception {
        Transaction newTransaction = this.transactionService.creatTransaction(transaction);
        return new ResponseEntity<>(newTransaction, HttpStatus.OK);
    }
}
