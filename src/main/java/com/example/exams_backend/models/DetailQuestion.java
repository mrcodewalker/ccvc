package com.example.exams_backend.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "detail_question")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetailQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "current_answer")
    private String currentAnswer;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @Column(name = "id_cau_truc")
    private String idCauTruc;

    @Column(name = "img")
    private String img;

    @Column(name = "modify")
    private boolean modify;

    @Column(name = "noi_dung")
    private String noiDung;

    @Column(name = "rtf")
    private String rtf;

    @Column(name = "su_dung")
    private String suDung;
    @Column(name = "true_answer")
    private String trueAnswer;
    @Column(name = "question_type")
    private String questionType;

    @OneToMany(mappedBy = "detailQuestion", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<DetailOption> detailOptions;
}
