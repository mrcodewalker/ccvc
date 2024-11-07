package com.example.exams_backend.services;

import com.example.exams_backend.dto.DetailOptionDTO;
import com.example.exams_backend.repository.OptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DetailOptionService implements IDetailOptionService{
    private final OptionRepository optionRepository;
    @Override
    public ResponseEntity<?> createOption(DetailOptionDTO detailOptionDTO) {
        return null;
    }

    @Override
    public ResponseEntity<?> updateOption(DetailOptionDTO detailOptionDTO) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteOption(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<?> getListOptionById(Long questionId) {
        return null;
    }
}
