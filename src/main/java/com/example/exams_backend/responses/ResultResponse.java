package com.example.exams_backend.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultResponse {
    @JsonProperty("correct_answer")
    private Long correctAnswer;
    @JsonProperty("total_question")
    private Long totalQuestion;
    @JsonProperty("username")
    private String username;
}
