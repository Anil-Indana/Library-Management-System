package com.example.LibraryManagementSystem.repositories;

import com.example.LibraryManagementSystem.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card,Integer> {
}
