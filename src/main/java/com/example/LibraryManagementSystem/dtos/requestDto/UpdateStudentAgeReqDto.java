package com.example.LibraryManagementSystem.dtos.requestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateStudentAgeReqDto {
    private int id;
    private int age;
}
