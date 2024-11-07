package com.example.exams_backend.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "scores")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Scores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "current_answer")
    private String currentAnswer;
    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private Account account;
    @Column(name = "created_at", updatable = false, nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    private DetailQuestion question;
}
