package com.example.LibraryManagementSystem.services.impl;

import com.example.LibraryManagementSystem.Exceptions.StudentNotFoundException;
import com.example.LibraryManagementSystem.dtos.requestDto.StudentRequestDto;
import com.example.LibraryManagementSystem.dtos.requestDto.UpdateStudentAgeReqDto;
import com.example.LibraryManagementSystem.dtos.requestDto.UpdateStudentMobNoRequestDto;
import com.example.LibraryManagementSystem.dtos.responseDto.CardResponseDto;
import com.example.LibraryManagementSystem.dtos.responseDto.StudentResponseDto;
import com.example.LibraryManagementSystem.dtos.responseDto.UpdateStudentResponseDto;
import com.example.LibraryManagementSystem.entities.Card;
import com.example.LibraryManagementSystem.entities.Student;
import com.example.LibraryManagementSystem.enums.CardStatus;
import com.example.LibraryManagementSystem.repositories.StudentRepository;
import com.example.LibraryManagementSystem.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Override
    public String addStudent(StudentRequestDto studentRequestDto) {
        // creating student From DTO
        Student student = new Student();
        student.setName(studentRequestDto.getName());
        student.setAge(studentRequestDto.getAge());
        student.setDepartment(studentRequestDto.getDepartment());
        student.setMobNo(studentRequestDto.getMobNo());

        // creating card for student
        Card card = new Card();
        card.setCardStatus(CardStatus.ACTIVE);
        card.setValidTill("2024-01-01");
        card.setStudent(student); // card - student
        student.setCard(card); // student - card
        // Saving it to DB
        studentRepository.save(student); // card will also be saved to Db as it is a child entity to student

        return "Student Added";
    }
    @Override
    public String deleteStudentById(Integer id){
        if(studentRepository.existsById(id)){
            studentRepository.deleteById(id);
            return "Student Deleted";
        }
        return "Student not Found";
    }

    @Override
    public UpdateStudentResponseDto updateStudentMobNo(UpdateStudentMobNoRequestDto updateStudentMobNoRequestDto) throws StudentNotFoundException {

        try{
            Student student = studentRepository.findById(updateStudentMobNoRequestDto.getId()).get();
            student.setMobNo(updateStudentMobNoRequestDto.getMobNo());
            Student updatedStudent = studentRepository.save(student);

            // response DTO
            UpdateStudentResponseDto studentResponseDto = new UpdateStudentResponseDto();
            studentResponseDto.setName(updatedStudent.getName());
            studentResponseDto.setMobNo(updateStudentMobNoRequestDto.getMobNo());
            studentResponseDto.setDepartment(updatedStudent.getDepartment());
            studentResponseDto.setAge(updatedStudent.getAge());
            return studentResponseDto;
        }
        catch (Exception e){
            throw new StudentNotFoundException("Invalid Student Id");
        }
    }

    @Override
    public StudentResponseDto findById(int id) throws StudentNotFoundException {
        Student student;
        try{
            student = studentRepository.findById(id).get();
            StudentResponseDto studentResponseDto = new StudentResponseDto();
            studentResponseDto.setName(student.getName());
            studentResponseDto.setMobNo(student.getMobNo());
            studentResponseDto.setDepartment(student.getDepartment());
            studentResponseDto.setAge(student.getAge());
            studentResponseDto.setId(student.getId());

            // CardResponse Dto
            CardResponseDto cardResponseDto = new CardResponseDto();
            Card card = student.getCard();
            cardResponseDto.setCardStatus(card.getCardStatus());
            cardResponseDto.setId(card.getId());
            cardResponseDto.setValidTill(card.getValidTill());
            cardResponseDto.setIssueDate(card.getIssueDate());

            studentResponseDto.setCardResponseDto(cardResponseDto);
            return studentResponseDto;
        }
        catch (Exception e){
            throw new StudentNotFoundException("Invalid Student Id");
        }
    }

    @Override
    public String updateAge(UpdateStudentAgeReqDto updateStudentAgeReqDto) throws StudentNotFoundException {
        try{
            Student student = studentRepository.findById(updateStudentAgeReqDto.getId()).get();
            student.setAge(updateStudentAgeReqDto.getAge());
            studentRepository.save(student);
            return "Age updated";
        }
        catch (Exception e){
            throw new StudentNotFoundException("Invalid Student Id");
        }
    }

    @Override
    public List<StudentResponseDto> findAll(){
        List<Student> list = studentRepository.findAll();
        List<StudentResponseDto> studentResponseDtos = new ArrayList<>();
        for(Student student : list){
            StudentResponseDto studentResponseDto = new StudentResponseDto();
            studentResponseDto.setId(student.getId());
            studentResponseDto.setName(student.getName());
            studentResponseDto.setDepartment(student.getDepartment());
            studentResponseDto.setAge(student.getAge());
            studentResponseDto.setMobNo(student.getMobNo());

            // CardResponseDto

            CardResponseDto cardResponseDto = new CardResponseDto();
            cardResponseDto.setIssueDate(student.getCard().getIssueDate());
            cardResponseDto.setId(student.getCard().getId());
            cardResponseDto.setCardStatus(student.getCard().getCardStatus());
            cardResponseDto.setIssueDate(student.getCard().getIssueDate());
            cardResponseDto.setValidTill(student.getCard().getValidTill());

            studentResponseDto.setCardResponseDto(cardResponseDto);

            studentResponseDtos.add(studentResponseDto);
        }
        return studentResponseDtos;
    }
}
