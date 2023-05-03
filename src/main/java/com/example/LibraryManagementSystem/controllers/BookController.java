package com.example.LibraryManagementSystem.controllers;

import com.example.LibraryManagementSystem.Exceptions.AuthorNotFoundException;
import com.example.LibraryManagementSystem.Exceptions.BookNotFoundException;
import com.example.LibraryManagementSystem.dtos.requestDto.BookRequestDto;
import com.example.LibraryManagementSystem.dtos.responseDto.BookResponseDto;
import com.example.LibraryManagementSystem.entities.Book;
import com.example.LibraryManagementSystem.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;
    @PostMapping("/add")
    public String addBook(@RequestBody BookRequestDto bookRequestDto) throws AuthorNotFoundException {
        return bookService.addBook(bookRequestDto);
    }
    @GetMapping("/allBooks")
    public List<BookResponseDto> findAllBooks(){
        return bookService.findAllBooks();
    }
    @GetMapping("/booksByAuthorId")
    public List<BookResponseDto> findBooksByAuthorId(@RequestParam("id")int id) throws AuthorNotFoundException{
        return bookService.findBooksByAuthorId(id);
    }
    @GetMapping("/noOfBooksByAuthor")
    public int noOfBooksByAuthor(@RequestParam("id")int id) throws AuthorNotFoundException{
        return bookService.noOfBooksByAuthor(id);
    }
    @GetMapping("/getBookById")
    public BookResponseDto getBookById(@RequestParam("id") int id) throws BookNotFoundException {
        return bookService.findBookById(id);
    }
}
