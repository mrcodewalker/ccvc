package com.example.exams_backend.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "student")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_code")
    private String studentCode;
    @Column(name = "fullname")
    private String fullName;
    @Column(name = "class")
    private String studentClass;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
}
