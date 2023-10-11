package com.dalencon.DesafioPicPay.services;

import com.dalencon.DesafioPicPay.dtos.UserDTO;
import com.dalencon.DesafioPicPay.repositories.UserRepository;
import com.dalencon.DesafioPicPay.usuario.User;
import com.dalencon.DesafioPicPay.usuario.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        if(!UserType.COMMON.equals(sender.getTypeUser())){
            throw new Exception("Usuário do tipo Logista não está autorizado a realizar transação");
        }
        if(sender.getBalance().compareTo(amount) < 0){
            throw new Exception("Saldo insuficiente");
        }
    }

    public User findUserById(Long id) throws Exception {
        return this.repository.findUserById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
    }

    public void saveUser(User user){
        this.repository.save(user);
    }

    public User createUser(UserDTO data){
        User newUser = new User(data);
        this.saveUser(newUser);
        return newUser;
    }

    public List<User> getAllUsers(){
        return this.repository.findAll();
    }
}
