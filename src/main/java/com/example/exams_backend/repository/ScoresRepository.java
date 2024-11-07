package com.example.exams_backend.repository;

import com.example.exams_backend.models.Scores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScoresRepository extends JpaRepository<Scores, Long> {
    Optional<Scores> findByAccountUsername(String username);
    @Query(value = """
        WITH RankedScores AS (
            SELECT
                s.id,
                s.username,
                s.question_id,
                s.current_answer,
                ROW_NUMBER() OVER (PARTITION BY s.question_id ORDER BY s.created_at DESC, s.id DESC) AS rn
            FROM scores s
            WHERE s.username = :username
        )
        SELECT
            id,
            username,
            question_id,
            current_answer
        FROM RankedScores
        WHERE rn = 1
        """, nativeQuery = true)
    List<Object[]> findLatestScoresForEachQuestion(@Param("username") String username);



}
