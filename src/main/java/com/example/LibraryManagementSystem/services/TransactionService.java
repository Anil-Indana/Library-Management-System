package com.example.LibraryManagementSystem.services;

import com.example.LibraryManagementSystem.Exceptions.BookNotFoundException;
import com.example.LibraryManagementSystem.Exceptions.CardNotFoundException;
import com.example.LibraryManagementSystem.dtos.requestDto.IssueBookRequestDto;
import com.example.LibraryManagementSystem.dtos.requestDto.ReturnBookRequestDto;
import com.example.LibraryManagementSystem.dtos.responseDto.IssueBookResponseDto;

public interface TransactionService {
    public IssueBookResponseDto issueBook(IssueBookRequestDto issueBookRequestDto) throws Exception;
    public String returnBook(ReturnBookRequestDto returnBookRequestDto) throws BookNotFoundException, CardNotFoundException;
}
