package com.example.exams_backend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "detail_option")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetailOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_cau_hoi")
    private String idCauHoi;

    @Column(name = "img")
    private String img;

    @Column(name = "noi_dung")
    private String noiDung;

    @Column(name = "rtf")
    private String rtf;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "detail_question_id")
    @JsonBackReference
    private DetailQuestion detailQuestion;
}
