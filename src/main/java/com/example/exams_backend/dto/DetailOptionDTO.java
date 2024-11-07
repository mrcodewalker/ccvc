package com.example.exams_backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetailOptionDTO {
    @JsonProperty("Id_CauHoi")
    private String idCauHoi;

    @JsonProperty("Img")
    private String img;

    @JsonProperty("NoiDung")
    private String noiDung;

    @JsonProperty("Rtf")
    private String rtf;
    @JsonProperty("DetailQuestionId")
    private String detailQuestionId;
}
