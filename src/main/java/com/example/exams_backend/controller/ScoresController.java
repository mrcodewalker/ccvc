package com.example.exams_backend.controller;

import com.example.exams_backend.dto.GetInformationDTO;
import com.example.exams_backend.dto.ScoresDTO;
import com.example.exams_backend.services.ScoresService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/scores")
public class ScoresController {
    private final ScoresService scoresService;
    @PostMapping("/create")
    public ResponseEntity<?> createScores(@RequestBody ScoresDTO scoresDTO){
        return ResponseEntity.ok(this.scoresService.createScore(scoresDTO));
    }
    @PostMapping("/complete")
    public ResponseEntity<?> doneExaminate(
            @RequestBody GetInformationDTO getInformationDTO
    ){
        return ResponseEntity.ok(this.scoresService.doneExam(getInformationDTO));
    }
    @GetMapping("/complete")
    public ResponseEntity<?> completeExam(@RequestBody GetInformationDTO getInformationDTO) throws IOException {
        return ResponseEntity.ok(this.scoresService.doneExam(getInformationDTO));
    }
}
