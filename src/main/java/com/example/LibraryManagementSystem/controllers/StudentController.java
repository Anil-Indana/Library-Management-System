package com.example.LibraryManagementSystem.controllers;

import com.example.LibraryManagementSystem.Exceptions.StudentNotFoundException;
import com.example.LibraryManagementSystem.dtos.requestDto.StudentRequestDto;
import com.example.LibraryManagementSystem.dtos.requestDto.UpdateStudentAgeReqDto;
import com.example.LibraryManagementSystem.dtos.requestDto.UpdateStudentMobNoRequestDto;
import com.example.LibraryManagementSystem.dtos.responseDto.StudentResponseDto;
import com.example.LibraryManagementSystem.dtos.responseDto.UpdateStudentResponseDto;
import com.example.LibraryManagementSystem.entities.Student;
import com.example.LibraryManagementSystem.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;
    @PostMapping("/add")
    public String addStudent(@RequestBody StudentRequestDto studentRequestDto){
        return studentService.addStudent(studentRequestDto);
    }
    @DeleteMapping("/deleteById")
    public String deleteById(@RequestParam("Id") Integer id){
        return studentService.deleteStudentById(id);
    }
    @PutMapping("/updateStudent")
    public UpdateStudentResponseDto updateStudent(@RequestBody UpdateStudentMobNoRequestDto updateStudentMobNoRequestDto) throws StudentNotFoundException {
        UpdateStudentResponseDto studentResponseDto = studentService.updateStudentMobNo(updateStudentMobNoRequestDto);
        return studentResponseDto;
    }
    @GetMapping("/findById")
    public StudentResponseDto findById(@RequestParam("Id") int id) throws StudentNotFoundException{
        return  studentService.findById(id);
    }
    @PutMapping("/updateAge")
    public String updateAge(@RequestBody UpdateStudentAgeReqDto updateStudentAgeReqDto) throws StudentNotFoundException{
        return studentService.updateAge(updateStudentAgeReqDto);
    }
    @GetMapping("/findAll")
    public List<StudentResponseDto> findAll(){
        return studentService.findAll();
    }
}
