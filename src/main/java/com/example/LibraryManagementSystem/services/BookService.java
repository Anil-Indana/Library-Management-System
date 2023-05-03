package com.example.LibraryManagementSystem.services;

import com.example.LibraryManagementSystem.Exceptions.AuthorNotFoundException;
import com.example.LibraryManagementSystem.Exceptions.BookNotFoundException;
import com.example.LibraryManagementSystem.dtos.requestDto.BookRequestDto;
import com.example.LibraryManagementSystem.dtos.responseDto.BookResponseDto;

import java.util.List;

public interface BookService {
    public String addBook(BookRequestDto bookRequestDto) throws AuthorNotFoundException;
    public List<BookResponseDto> findAllBooks();
    public List<BookResponseDto> findBooksByAuthorId(int authorId) throws AuthorNotFoundException;
    public int noOfBooksByAuthor(int id) throws  AuthorNotFoundException;
    public BookResponseDto findBookById(int id) throws BookNotFoundException;
}
