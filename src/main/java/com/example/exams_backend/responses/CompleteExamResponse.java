package com.example.exams_backend.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompleteExamResponse {
    @JsonProperty("status")
    private String status;
    @JsonProperty("correct_answer")
    private String correctAnswer;
    @JsonProperty("total_answered")
    private String totalAnswered;
}
