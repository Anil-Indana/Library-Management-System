package com.example.LibraryManagementSystem.dtos.responseDto;

import com.example.LibraryManagementSystem.entities.Book;
import com.example.LibraryManagementSystem.enums.CardStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CardResponseDto {
    private int id;
    private CardStatus cardStatus;
    private String validTill;
    private Date issueDate;
}
