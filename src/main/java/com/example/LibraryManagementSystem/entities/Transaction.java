package com.example.LibraryManagementSystem.entities;

import com.example.LibraryManagementSystem.enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;

    private boolean issueOperation;

    private String transactionNumber;

    @CreationTimestamp
    private Date transactionDate;

    @ManyToOne
    @JoinColumn
    private Card card;

    @ManyToOne
    @JoinColumn
    private Book book;
}
