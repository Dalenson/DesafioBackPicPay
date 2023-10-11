package com.dalencon.DesafioPicPay.services;

import com.dalencon.DesafioPicPay.dtos.NotificationDTO;
import com.dalencon.DesafioPicPay.dtos.TransactionDTO;
import com.dalencon.DesafioPicPay.repositories.TransactionRepository;
import com.dalencon.DesafioPicPay.transaction.Transaction;
import com.dalencon.DesafioPicPay.usuario.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {
    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NotificationService notificationService;

    public Transaction creatTransaction(TransactionDTO transaction) throws Exception {
        User sender = this.userService.findUserById(transaction.senderId());
        User receiver = this.userService.findUserById(transaction.receiverId());

        userService.validateTransaction(sender, transaction.value());

        Boolean isAuthorized = this.autorizeTransaction(sender, transaction.value());
        if(!isAuthorized){
            throw new Exception("Transação não autorizada");
        }

        Transaction newTrasaction = new Transaction();
        newTrasaction.setAmount(transaction.value());
        newTrasaction.setSender(sender);
        newTrasaction.setReceiver(receiver);
        newTrasaction.setTimetamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transaction.value()));
        receiver.setBalance(receiver.getBalance().add(transaction.value()));

        this.repository.save(newTrasaction);
        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);

        this.notificationService.sendNotification(sender, "Transação realizada com sucesso");

        this.notificationService.sendNotification(receiver, "Transação recebida com sucesso");

        return newTrasaction;
    }

    public Boolean autorizeTransaction(User sender, BigDecimal value){
        ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity("https://y79go.wiremockapi.cloud/json/1", Map.class);

        if(HttpStatus.OK.equals(authorizationResponse.getStatusCode())){
            String message = (String) authorizationResponse.getBody().get("authorize");
            return "true".equalsIgnoreCase(message);
        } else return false;
    }
}
