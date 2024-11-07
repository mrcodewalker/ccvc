package com.example.exams_backend.services;

import com.example.exams_backend.models.Result;
import com.example.exams_backend.models.Scores;
import com.example.exams_backend.repository.ResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResultService implements IResultService{
    private final ResultRepository resultRepository;

    @Override
    public ResponseEntity<?> createResult(Scores scores) {
        return null;
    }

    @Override
    public ResponseEntity<?> updateResult(Scores scores) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteResult(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<?> getResult(String username) {
        return null;
    }
    public Result getResultByUserName(String username){
        if (this.resultRepository.findByAccountUsername(username).isPresent()){
            return this.resultRepository.findByAccountUsername(username).get();
        }
        return null;
    }
}
