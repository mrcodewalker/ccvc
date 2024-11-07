package com.example.exams_backend.services;

import com.example.exams_backend.dto.LoginDTO;
import com.example.exams_backend.dto.ScoresDTO;
import com.example.exams_backend.dto.StudentDTO;
import com.example.exams_backend.models.Student;
import org.springframework.http.ResponseEntity;

public interface IStudentService {
    ResponseEntity<?> createStudent(StudentDTO studentDTO);
    ResponseEntity<?> updateStudent(StudentDTO studentDTO);
    ResponseEntity<?> deleteStudent(Long id);
    ResponseEntity<?> getStudent(String studentCode);
    Student findStudentByStudentCode(String studentCode);
    ResponseEntity<?> login(LoginDTO loginDTO);
}
