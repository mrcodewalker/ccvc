package com.example.exams_backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ResultDTO {
    @JsonProperty("count_correct_answer")
    public String count_correct_answer;
    @JsonProperty("username")
    private String username;
}
