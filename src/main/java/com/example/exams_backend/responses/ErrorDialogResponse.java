package com.example.exams_backend.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorDialogResponse {
    @JsonProperty("message")
    private String message;
    @JsonProperty("title")
    private String title;
    @JsonProperty("error")
    private boolean error;
}
