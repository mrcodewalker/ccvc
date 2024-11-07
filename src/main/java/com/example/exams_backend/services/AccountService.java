package com.example.exams_backend.services;

import com.example.exams_backend.dto.AccountDTO;
import com.example.exams_backend.models.Account;
import com.example.exams_backend.models.Student;
import com.example.exams_backend.repository.AccountRepository;
import com.example.exams_backend.repository.StudentRepository;
import com.example.exams_backend.responses.StudentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService implements IAccountService{
    private final AccountRepository accountRepository;
    private final StudentRepository studentRepository;
    @Lazy
    @Autowired
    private StudentService studentService;
    @Override
    public ResponseEntity<?> createAccount(AccountDTO accountDTO) {
        Optional<Student> studentOpt = studentRepository.findByStudentCode(accountDTO.getStudentCode());

        if (studentOpt.isPresent()) {
            Account account = Account.builder()
                    .passwordEncoded(accountDTO.getPassword())
                    .student(studentOpt.get())
                    .username(accountDTO.getUsername())
                    .build();

            return ResponseEntity.ok(accountRepository.save(account));
        } else {
            throw new RuntimeException("Student not found for code: " + accountDTO.getStudentCode());
        }
    }

    @Override
    public ResponseEntity<?> updateAccount(AccountDTO accountDTO) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteAccount(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<?> getAccount(String username) {
        return null;
    }

    @Override
    public Account findAccountByUserName(String username) {
        Optional<Account> account = this.accountRepository.findByUsername(username);
        return account.orElse(null);
    }
    @Override
    public StudentResponse getInformation(String username){
        Account account = this.findAccountByUserName(username);
        if (account==null){
            throw new RuntimeException("Can not get information right now!");
        }
        Student student = this.studentService
                .findStudentByStudentCode(account.getStudent().getStudentCode());
        if (student==null){
            throw new RuntimeException("Can not get information right now!");
        }
        return StudentResponse.builder()
                .studentCode(student.getStudentCode())
                .studentClass(student.getStudentClass())
                .email(student.getEmail())
                .phone(student.getPhone())
                .fullName(student.getFullName())
                .build();
    }

}
