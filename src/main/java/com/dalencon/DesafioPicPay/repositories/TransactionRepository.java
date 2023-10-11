package com.dalencon.DesafioPicPay.repositories;

import com.dalencon.DesafioPicPay.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
