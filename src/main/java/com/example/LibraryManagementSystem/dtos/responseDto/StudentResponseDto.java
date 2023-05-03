package com.example.LibraryManagementSystem.dtos.responseDto;

import com.example.LibraryManagementSystem.enums.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class StudentResponseDto {
    private String name;
    private Department department;
    private String mobNo;
    private int age;
    private int id;
    private CardResponseDto cardResponseDto;
}
