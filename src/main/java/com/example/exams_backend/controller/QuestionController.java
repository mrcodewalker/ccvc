package com.example.exams_backend.controller;

import com.example.exams_backend.dto.DetailQuestionDTO;
import com.example.exams_backend.dto.GetInformationDTO;
import com.example.exams_backend.services.DetailQuestionService;
import com.example.exams_backend.services.ScoresService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/questions")
//@CrossOrigin(origins = "https://kma-legend.onrender.com")
public class QuestionController {
    private final DetailQuestionService detailQuestionService;
    private final ScoresService scoresService;

    @GetMapping("/data")
    public ResponseEntity<?> login() throws IOException {
        return ResponseEntity.ok("Hello World!");
    }
    @PostMapping("/generate")
    public ResponseEntity<?> generateQuestion() throws IOException {
        return ResponseEntity.ok(this.detailQuestionService
                .createQuestion());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getQuestion(@PathVariable Long id) throws IOException {
        return ResponseEntity.ok(this.detailQuestionService
                .getQuestion(id+1));
    }
    @GetMapping("/collect")
    public ResponseEntity<?> collectAll() throws Exception {
        return ResponseEntity.ok(this.detailQuestionService.collectAll());
    }
    @PostMapping("/review")
    public ResponseEntity<?> getAnswer(@RequestBody DetailQuestionDTO detailQuestionDTO) throws IOException {
        return this.detailQuestionService
                .getAnswer(detailQuestionDTO);
    }
    @GetMapping("/find/all")
    public ResponseEntity<?> collectAllQuestions(
            @RequestParam("page") int page,
            @RequestParam(value = "size", defaultValue = "50") int size
    ) throws Exception {
        return ResponseEntity.ok(this.detailQuestionService.findAll(page, size));
    }
}
