package com.example.exams_backend.dto;

import com.example.exams_backend.models.Account;
import com.example.exams_backend.models.Student;
import com.example.exams_backend.repository.StudentRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.swing.text.html.Option;
import java.util.Optional;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class AccountDTO {
    private final StudentRepository studentRepository;
    @JsonProperty("student_code")
    private String studentCode;
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
    public Account formData(AccountDTO accountDTO){
        Optional<Student> student = this.studentRepository.findByStudentCode(accountDTO.getStudentCode());
        if (student.isPresent()) {
            return Account.builder()
                    .passwordEncoded(accountDTO.password)
                    .student(student.get())
                    .username(accountDTO.getUsername())
                    .build();
        }
        return null;
    }
}
