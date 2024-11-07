package com.example.exams_backend.services;

import com.example.exams_backend.dto.DetailQuestionDTO;
import com.example.exams_backend.models.DetailQuestion;
import com.example.exams_backend.responses.ListQuestionsResponse;
import com.example.exams_backend.responses.QuestionResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IDetailQuestionService {
    ResponseEntity<?> createQuestion();
    ResponseEntity<?> updateQuestion(DetailQuestionDTO detailQuestionDTO);
    ResponseEntity<?> getAnswer(DetailQuestionDTO detailQuestionDTO);
    ResponseEntity<?> deleteQuestion(Long id);
    QuestionResponse getQuestion(Long id);
    List<QuestionResponse> collectAll() throws Exception;
    QuestionResponse collectOneQuestion() throws Exception;
    ResponseEntity<?> completeExam();
    ListQuestionsResponse findAll(int page, int size) throws Exception;
    DetailQuestion findQuestionById(Long id);
}
