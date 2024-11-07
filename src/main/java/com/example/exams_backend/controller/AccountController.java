package com.example.exams_backend.controller;

import com.example.exams_backend.dto.AccountDTO;
import com.example.exams_backend.dto.GetInformationDTO;
import com.example.exams_backend.services.AccountService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/account")
public class AccountController {
    private final AccountService accountService;
    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@RequestBody AccountDTO accountDTO){
        return this.accountService.createAccount(accountDTO);
    }
    @PostMapping("/info")
    public ResponseEntity<?> getInformation(@RequestBody GetInformationDTO getInformationDTO){
        return ResponseEntity.ok(this.accountService.getInformation(getInformationDTO.getUsername()));
    }
}
