package com.example.LibraryManagementSystem.services;

import com.example.LibraryManagementSystem.dtos.requestDto.AuthorRequestDto;
import com.example.LibraryManagementSystem.dtos.responseDto.AuthorResponseDto;

import java.util.List;

public interface AuthorService {
    public String addAuthor(AuthorRequestDto authorRequestDto);
    public String deleteById(Integer id);
    public AuthorResponseDto getAuthorByEmail(String email);
    public List<AuthorResponseDto> authorsOfGivenAge(int age);
}
