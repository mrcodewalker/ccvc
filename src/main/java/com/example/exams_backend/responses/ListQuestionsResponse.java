package com.example.exams_backend.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListQuestionsResponse {
    @JsonProperty("question")
    private List<QuestionResponse> questionResponses;
    @JsonProperty("total_page")
    private int totalPages;
}
