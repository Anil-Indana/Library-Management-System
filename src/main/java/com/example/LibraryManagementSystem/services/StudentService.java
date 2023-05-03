package com.example.LibraryManagementSystem.services;

import com.example.LibraryManagementSystem.Exceptions.StudentNotFoundException;
import com.example.LibraryManagementSystem.dtos.requestDto.StudentRequestDto;
import com.example.LibraryManagementSystem.dtos.requestDto.UpdateStudentAgeReqDto;
import com.example.LibraryManagementSystem.dtos.requestDto.UpdateStudentMobNoRequestDto;
import com.example.LibraryManagementSystem.dtos.responseDto.StudentResponseDto;
import com.example.LibraryManagementSystem.dtos.responseDto.UpdateStudentResponseDto;
import com.example.LibraryManagementSystem.entities.Card;
import com.example.LibraryManagementSystem.entities.Student;

import java.util.List;

public interface StudentService {
    public String addStudent(StudentRequestDto studentRequestDto);
    public String deleteStudentById(Integer id);
    public UpdateStudentResponseDto updateStudentMobNo(UpdateStudentMobNoRequestDto updateStudentMobNoRequestDto) throws StudentNotFoundException;
    public StudentResponseDto findById(int id) throws StudentNotFoundException;
    public String updateAge(UpdateStudentAgeReqDto updateStudentAgeReqDto) throws StudentNotFoundException;
    public List<StudentResponseDto> findAll();
}
