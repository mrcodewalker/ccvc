package com.example.exams_backend.dto;

import com.example.exams_backend.models.Student;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class StudentDTO {
    @JsonProperty("student_code")
    private String studentCode;
    @JsonProperty("fullname")
    private String fullName;
    @JsonProperty("class")
    private String studentClass;
    @JsonProperty("email")
    private String email;
    @JsonProperty("phone")
    private String phone;
    public static Student formData(StudentDTO studentDTO){
        return Student.builder()
                .email(studentDTO.getEmail())
                .fullName(studentDTO.getFullName())
                .studentClass(studentDTO.getStudentClass())
                .studentCode(studentDTO.getStudentCode())
                .phone(studentDTO.getPhone())
                .build();
    }
}
