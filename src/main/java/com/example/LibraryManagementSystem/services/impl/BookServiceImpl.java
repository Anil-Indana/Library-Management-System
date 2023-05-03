package com.example.LibraryManagementSystem.services.impl;

import com.example.LibraryManagementSystem.Exceptions.AuthorNotFoundException;
import com.example.LibraryManagementSystem.Exceptions.BookNotFoundException;
import com.example.LibraryManagementSystem.dtos.requestDto.BookRequestDto;
import com.example.LibraryManagementSystem.dtos.responseDto.AuthorResponseDto;
import com.example.LibraryManagementSystem.dtos.responseDto.BookResponseDto;
import com.example.LibraryManagementSystem.entities.Book;
import com.example.LibraryManagementSystem.entities.Author;
import com.example.LibraryManagementSystem.repositories.AuthorRepository;
import com.example.LibraryManagementSystem.repositories.BookRepository;
import com.example.LibraryManagementSystem.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    BookRepository bookRepository;
    public String addBook(BookRequestDto bookRequestDto) throws AuthorNotFoundException {
        Author author;
        try{
            // checking if author exists or not
            author = authorRepository.findById(bookRequestDto.getAuthor().getId()).get();
        }
        catch (Exception e){
            throw new AuthorNotFoundException("Author not found");
        }
        Book book = new Book();
        book.setAuthor(author);
        book.setGenre(bookRequestDto.getGenre());
        book.setTitle(bookRequestDto.getTitle());
        book.setPrice(bookRequestDto.getPrice());
        book.setNoOfPages(bookRequestDto.getNoOfPages());
        author.getBooks().add(book); // adding book to author
        book.setAuthor(author); // setting the author for book
        authorRepository.save(author); // will save the book as well while saving author
        return "Book Added";
    }

    @Override
    public List<BookResponseDto> findAllBooks() {
        List<Book> books = bookRepository.findAll();
        List<BookResponseDto> list = new ArrayList<>();
        for(Book book : books){
            BookResponseDto bookResponseDto = new BookResponseDto();
            bookResponseDto.setId(book.getId());
            bookResponseDto.setGenre(book.getGenre());
            bookResponseDto.setTitle(book.getTitle());
            AuthorResponseDto authorResponseDto = new AuthorResponseDto();
            authorResponseDto.setName(book.getAuthor().getName());
            authorResponseDto.setAge(book.getAuthor().getAge());
            bookResponseDto.setAuthorResponseDto(authorResponseDto);
            list.add(bookResponseDto);
        }
        return list;
    }

    @Override
    public List<BookResponseDto> findBooksByAuthorId(int authorId) throws AuthorNotFoundException{
        Author author;
        try{
            author = authorRepository.findById(authorId).get();
            List<Book> books = author.getBooks();
            List<BookResponseDto> list = new ArrayList<>();
            for(Book book : books){
                BookResponseDto bookResponseDto = new BookResponseDto();
                bookResponseDto.setId(book.getId());
                bookResponseDto.setGenre(book.getGenre());
                bookResponseDto.setTitle(bookResponseDto.getTitle());
                AuthorResponseDto authorResponseDto = new AuthorResponseDto();
                authorResponseDto.setName(book.getAuthor().getName());
                authorResponseDto.setAge(book.getAuthor().getAge());
                bookResponseDto.setAuthorResponseDto(authorResponseDto);
                list.add(bookResponseDto);
            }
            return list;
        }
        catch (Exception e){
            throw new AuthorNotFoundException("InValid Author Id");
        }
    }

    @Override
    public int noOfBooksByAuthor(int id) throws AuthorNotFoundException {
        Author author;
        try{
            author = authorRepository.findById(id).get();
            int count = author.getBooks().size();
            return count;
        }
        catch (Exception e){
            throw new AuthorNotFoundException("Invalid Author Id");
        }
    }

    @Override
    public BookResponseDto findBookById(int id) throws BookNotFoundException {
        Book book;
        try{
            book = bookRepository.findById(id).get();
            BookResponseDto bookResponseDto = new BookResponseDto();
            bookResponseDto.setTitle(book.getTitle());
            bookResponseDto.setId(book.getId());
            bookResponseDto.setGenre(book.getGenre());
            AuthorResponseDto authorResponseDto = new AuthorResponseDto();
            authorResponseDto.setName(book.getAuthor().getName());
            authorResponseDto.setAge(book.getAuthor().getAge());
            bookResponseDto.setAuthorResponseDto(authorResponseDto);
            return bookResponseDto;
        }
        catch(Exception e){
            throw new BookNotFoundException("Invalid Book id");
        }
    }

}
