package com.example.exams_backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetInformationDTO {
    @JsonProperty("username")
    private String username;
}
