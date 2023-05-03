package com.example.LibraryManagementSystem.repositories;

import com.example.LibraryManagementSystem.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
}
