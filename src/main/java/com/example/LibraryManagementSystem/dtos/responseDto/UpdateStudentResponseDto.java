package com.example.LibraryManagementSystem.dtos.responseDto;

import com.example.LibraryManagementSystem.entities.Card;
import com.example.LibraryManagementSystem.enums.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateStudentResponseDto {
    private String name;
    private String mobNo;
    private int age;
    private Department department;
    private Card card;
}
