package com.learning.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
