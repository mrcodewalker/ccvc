package com.example.exams_backend.services;

import com.example.exams_backend.dto.LoginDTO;
import com.example.exams_backend.dto.StudentDTO;
import com.example.exams_backend.models.Account;
import com.example.exams_backend.models.Student;
import com.example.exams_backend.repository.StudentRepository;
import com.example.exams_backend.responses.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService{
    private final StudentRepository studentRepository;
    private final AccountService accountService;

    @Override
    public ResponseEntity<?> createStudent(StudentDTO studentDTO) {
        Optional<Student> student = this.studentRepository.findByStudentCode(studentDTO.getStudentCode());
        if (student.isPresent()){
            return ResponseEntity.ok("Can not create a new student!");
        }
        return ResponseEntity.ok(this.studentRepository.save(StudentDTO.formData(studentDTO)));
    }

    @Override
    public ResponseEntity<?> updateStudent(StudentDTO studentDTO) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteStudent(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<?> getStudent(String studentCode) {
        return null;
    }

    @Override
    public Student findStudentByStudentCode(String studentCode) {
        Optional<Student> student = this.studentRepository.findByStudentCode(studentCode);
        return student.orElse(null);
    }

    @Override
    public ResponseEntity<?> login(LoginDTO loginDTO) {
        Account account = this.accountService
                .findAccountByUserName(loginDTO.getUsername());
        if (account==null||loginDTO.getPassword()==null){
            return ResponseEntity.ok(LoginResponse.builder()
                            .message("Can not login right now")
                            .status("404")
                    .build());
        }
        if (loginDTO.getPassword().equals(account.getPasswordEncoded()))
        {
            return ResponseEntity.ok(
                    LoginResponse.builder()
                            .status("200")
                            .message("Login successfully!")
                            .build()
            );
        }
        return ResponseEntity.ok(LoginResponse.builder()
                .message("Please check your information again!")
                .status("401")
                .build());
    }
}
