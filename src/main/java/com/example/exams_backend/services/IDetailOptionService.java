package com.example.exams_backend.services;

import com.example.exams_backend.dto.DetailOptionDTO;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface IDetailOptionService {
    ResponseEntity<?> createOption(DetailOptionDTO detailOptionDTO);
    ResponseEntity<?> updateOption(DetailOptionDTO detailOptionDTO);
    ResponseEntity<?> deleteOption(Long id);
    ResponseEntity<?> getListOptionById(Long questionId);
}
