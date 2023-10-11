package com.dalencon.DesafioPicPay.dtos;

import com.dalencon.DesafioPicPay.usuario.UserType;

import java.math.BigDecimal;

public record UserDTO(String firstName, String lastName, String document, BigDecimal balance, String email, String password, UserType userType) {
}
