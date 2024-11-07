package com.example.exams_backend.repository;

import com.example.exams_backend.models.DetailQuestion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<DetailQuestion, Long> {
    Optional<DetailQuestion> findById(Long id);
    @Query(value = "SELECT * FROM detail_question ORDER BY RAND() LIMIT 50", nativeQuery = true)
    List<DetailQuestion> findRandomQuestions();
    @Query(value = "SELECT * FROM detail_question", nativeQuery = true)
    Page<DetailQuestion> findAll(Pageable pageable);
}
