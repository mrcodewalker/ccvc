package com.example.exams_backend.services;

import com.example.exams_backend.dto.DetailOptionDTO;
import com.example.exams_backend.dto.GetInformationDTO;
import com.example.exams_backend.dto.ScoresDTO;
import com.example.exams_backend.responses.ResultResponse;
import org.springframework.http.ResponseEntity;

public interface IScoresService {
    ResponseEntity<?> createScore(ScoresDTO scoresDTO);


    ResultResponse doneExam(GetInformationDTO getInformationDTO);

    ResponseEntity<?> updateScore(ScoresDTO scoresDTO);
    ResponseEntity<?> deleteScores(Long id);
    ResponseEntity<?> getUser(String username);
}
