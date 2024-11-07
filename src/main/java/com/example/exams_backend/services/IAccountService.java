package com.example.exams_backend.services;

import com.example.exams_backend.dto.AccountDTO;
import com.example.exams_backend.models.Account;
import com.example.exams_backend.responses.StudentResponse;
import org.springframework.http.ResponseEntity;

public interface IAccountService {
    ResponseEntity<?> createAccount(AccountDTO accountDTO);
    ResponseEntity<?> updateAccount(AccountDTO accountDTO);
    ResponseEntity<?> deleteAccount(Long id);
    ResponseEntity<?> getAccount(String username);
    Account findAccountByUserName(String username);

    StudentResponse getInformation(String username);
}
