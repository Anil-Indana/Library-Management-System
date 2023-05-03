package com.example.LibraryManagementSystem.dtos.requestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStudentMobNoRequestDto {
    private int id;
    private String mobNo;
}
