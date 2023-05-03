package com.example.LibraryManagementSystem.services.impl;

import com.example.LibraryManagementSystem.Exceptions.BookNotFoundException;
import com.example.LibraryManagementSystem.Exceptions.CardNotFoundException;
import com.example.LibraryManagementSystem.dtos.requestDto.IssueBookRequestDto;
import com.example.LibraryManagementSystem.dtos.requestDto.ReturnBookRequestDto;
import com.example.LibraryManagementSystem.dtos.responseDto.IssueBookResponseDto;
import com.example.LibraryManagementSystem.entities.Book;
import com.example.LibraryManagementSystem.entities.Card;
import com.example.LibraryManagementSystem.entities.Transaction;
import com.example.LibraryManagementSystem.enums.CardStatus;
import com.example.LibraryManagementSystem.enums.TransactionStatus;
import com.example.LibraryManagementSystem.repositories.BookRepository;
import com.example.LibraryManagementSystem.repositories.CardRepository;
import com.example.LibraryManagementSystem.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements com.example.LibraryManagementSystem.services.TransactionService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public IssueBookResponseDto issueBook(IssueBookRequestDto issueBookRequestDto) throws Exception {
        // first we have to check if the book and card exists with the input id
        // Irrespective of the existence of book and card....transaction has to be stored
        Transaction transaction = new Transaction();
        transaction.setTransactionNumber(String.valueOf(UUID.randomUUID()));
        transaction.setIssueOperation(true);
        Book book;
        try{
           book =  bookRepository.findById(issueBookRequestDto.getBookId()).get();
        }
        catch (Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw  new BookNotFoundException("Invalid Book Id");
        }
        Card card;
        try{
            card = cardRepository.findById(issueBookRequestDto.getCardId()).get();
        }
        catch (Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new CardNotFoundException("Invalid Card Id");
        }

        transaction.setBook(book);
        if(card.getCardStatus() != CardStatus.ACTIVE){ // check whether card is active or not
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("card is Inactive");
        }

        // checking if book is already issued or not
        if(book.isIssued()){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("Book is not Available");
        }

        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        transaction.setCard(card);

        book.setIssued(true);
        book.getTransactionList().add(transaction);
        book.setCard(card);

        card.getTransactionList().add(transaction);
        card.getBooksIssued().add(book);

        cardRepository.save(card); // it'll save card , book, transaction - as book and transaction are child entities of card

        IssueBookResponseDto issueBookResponseDto = new IssueBookResponseDto();
        issueBookResponseDto.setBookName(book.getTitle());
        issueBookResponseDto.setTransactionNumber(transaction.getTransactionNumber());
        issueBookResponseDto.setTransactionStatus(TransactionStatus.SUCCESS);

        return issueBookResponseDto;
    }
    @Override
    public String returnBook(ReturnBookRequestDto returnBookRequestDto) throws BookNotFoundException,CardNotFoundException{
        Transaction transaction = new Transaction();
        transaction.setTransactionNumber(String.valueOf(UUID.randomUUID()));
        Book book; // to check bookId is valid
        try{
            book = bookRepository.findById(returnBookRequestDto.getBookId()).get();
        }
        catch (Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new BookNotFoundException("Invalid book Id");
        }
        Card card;
        try{
            card = cardRepository.findById(returnBookRequestDto.getCardId()).get();
        }
        catch (Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new CardNotFoundException("Invalid card ID");
        }
        if(!book.isIssued()){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new BookNotFoundException("Book is not issued");
        }
        transaction.setTransactionStatus(TransactionStatus.FAILED);

        book.setIssued(false);
        book.getTransactionList().add(transaction);
        book.setCard(card);

        card.getTransactionList().add(transaction);
        card.getBooksIssued().remove(book);

        cardRepository.save(card);

        return  "Book returned Successfully";
    }
}
