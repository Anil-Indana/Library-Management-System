package com.example.LibraryManagementSystem.dtos.responseDto;

import com.example.LibraryManagementSystem.enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseDto {
    private int id;
    private String title;
    private Genre genre;
    private AuthorResponseDto authorResponseDto;
}
