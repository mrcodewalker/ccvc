package com.example.exams_backend.services;

import com.example.exams_backend.dto.LoginDTO;
import com.example.exams_backend.dto.StudentDTO;
import com.example.exams_backend.models.Scores;
import com.example.exams_backend.models.Student;
import org.springframework.http.ResponseEntity;

public interface IResultService {
    ResponseEntity<?> createResult(Scores scores);
    ResponseEntity<?> updateResult(Scores scores);
    ResponseEntity<?> deleteResult(Long id);
    ResponseEntity<?> getResult(String username);
}
