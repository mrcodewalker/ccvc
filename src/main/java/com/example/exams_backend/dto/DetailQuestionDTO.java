package com.example.exams_backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class DetailQuestionDTO {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("CurrentAnswer")
    private String currentAnswer;

    @JsonProperty("DapAn")
    private List<DetailOptionDTO> detailOptionDTO;

    @JsonProperty("GhiChu")
    private String ghiChu;

    @JsonProperty("Id_CauTruc")
    private String idCauTruc;

    @JsonProperty("Img")
    private String img;

    @JsonProperty("Modify")
    private boolean modify;

    @JsonProperty("NoiDung")
    private String noiDung;

    @JsonProperty("Rtf")
    private String rtf;

    @JsonProperty("SuDung")
    private String suDung;
}
