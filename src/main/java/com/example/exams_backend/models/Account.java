package com.example.exams_backend.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "account")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password_encoded", nullable = false)
    private String passwordEncoded;

    @ManyToOne
    @JoinColumn(name = "student_code", referencedColumnName = "student_code")
    private Student student;
}
