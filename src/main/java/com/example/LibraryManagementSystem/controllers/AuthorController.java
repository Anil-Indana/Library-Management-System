package com.example.LibraryManagementSystem.controllers;

import com.example.LibraryManagementSystem.dtos.requestDto.AuthorRequestDto;
import com.example.LibraryManagementSystem.dtos.responseDto.AuthorResponseDto;
import com.example.LibraryManagementSystem.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    AuthorService authorService;
    @PostMapping("/add")
    public String addAuthor(@RequestBody AuthorRequestDto authorRequestDto){
        return authorService.addAuthor(authorRequestDto);
    }
    @DeleteMapping("/deleteById")
    public String deleteById(@RequestParam("Id") Integer id){
        return authorService.deleteById(id);
    }
    @GetMapping("/getAuthorByEmail")
    public AuthorResponseDto getAuthorByEmail(String email) {
        return authorService.getAuthorByEmail(email);
    }
    @GetMapping("/authorsOfParticularAge")
    public List<AuthorResponseDto> authorsOfGivenAge(@RequestParam("id")int id){
        return authorService.authorsOfGivenAge(id);
    }
}
