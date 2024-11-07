package com.example.exams_backend.responses;

import com.example.exams_backend.dto.DetailOptionDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class QuestionAdminResponse {
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
    @JsonProperty("question_type")
    private String questionType;
}
