package com.example.LibraryManagementSystem.dtos.requestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class IssueBookRequestDto {
    private int cardId;
    private int bookId;
}
