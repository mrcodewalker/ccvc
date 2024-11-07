package com.example.exams_backend.dto;

import com.example.exams_backend.models.Account;
import com.example.exams_backend.models.Scores;
import com.example.exams_backend.repository.QuestionRepository;
import com.example.exams_backend.responses.QuestionResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.stereotype.Repository;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ScoresDTO {
    @JsonProperty("current_answer")
    public String current_answer;
    @JsonProperty("question_id")
    public String questionId;
    @JsonProperty("username")
    private String username;

}
