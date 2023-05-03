package com.example.LibraryManagementSystem.dtos.requestDto;

import com.example.LibraryManagementSystem.entities.Author;
import com.example.LibraryManagementSystem.enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookRequestDto {
    private String title;
    private int price;
    private int noOfPages;
    private Author author;
    private Genre genre;
}
