package com.example.exams_backend.repository;

import com.example.exams_backend.models.DetailOption;
import com.example.exams_backend.models.DetailQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OptionRepository extends JpaRepository<DetailOption, Long> {
    List<DetailOption> findByDetailQuestionId(Long id);
}