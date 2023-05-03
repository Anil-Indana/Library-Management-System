package com.example.LibraryManagementSystem.services.impl;

import com.example.LibraryManagementSystem.dtos.requestDto.AuthorRequestDto;
import com.example.LibraryManagementSystem.dtos.responseDto.AuthorResponseDto;
import com.example.LibraryManagementSystem.entities.Author;
import com.example.LibraryManagementSystem.repositories.AuthorRepository;
import com.example.LibraryManagementSystem.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    AuthorRepository authorRepository;
    @Override
    public String addAuthor(AuthorRequestDto authorRequestDto){
        Author author = new Author();
        author.setAge(authorRequestDto.getAge());
        author.setName(authorRequestDto.getName());
        author.setEmail(authorRequestDto.getEmail());
        authorRepository.save(author);
        return "Author added";
    }

    @Override
    public String deleteById(Integer id) {
        if(authorRepository.existsById(id)){
            authorRepository.deleteById(id);
            return "Author deleted";
        }
        return "Author doesn't exist";
    }

    @Override
    public AuthorResponseDto getAuthorByEmail(String email) {
        Author author = authorRepository.findByEmail(email);

        // preparing ResponseDTO
        AuthorResponseDto authorResponseDto = new AuthorResponseDto();
        authorResponseDto.setName(author.getName());
        authorResponseDto.setAge(author.getAge());

        return authorResponseDto;
    }

    @Override
    public List<AuthorResponseDto> authorsOfGivenAge(int age) {
        List<Author> authors = authorRepository.findAll();
        List<AuthorResponseDto> list = new ArrayList<>();
        for(Author author : authors){
            if(author.getAge() == age){
                AuthorResponseDto authorResponseDto = new AuthorResponseDto();
                authorResponseDto.setAge(author.getAge());
                authorResponseDto.setName(author.getName());
                list.add(authorResponseDto);
            }
        }
        return list;
    }
}
