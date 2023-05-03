package com.example.LibraryManagementSystem.controllers;

import com.example.LibraryManagementSystem.Exceptions.BookNotFoundException;
import com.example.LibraryManagementSystem.Exceptions.CardNotFoundException;
import com.example.LibraryManagementSystem.dtos.requestDto.IssueBookRequestDto;
import com.example.LibraryManagementSystem.dtos.requestDto.ReturnBookRequestDto;
import com.example.LibraryManagementSystem.dtos.responseDto.IssueBookResponseDto;
import com.example.LibraryManagementSystem.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;
    @PostMapping("/issueBook")
    public IssueBookResponseDto issueBook(@RequestBody IssueBookRequestDto issueBookRequestDto) throws Exception{
        return transactionService.issueBook(issueBookRequestDto);
    }
    @PostMapping("/returnBook")
    public String returnBook(@RequestBody ReturnBookRequestDto returnBookRequestDto) throws BookNotFoundException, CardNotFoundException {
       return  transactionService.returnBook(returnBookRequestDto);
    }
}
