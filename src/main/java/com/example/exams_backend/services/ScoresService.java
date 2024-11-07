package com.example.exams_backend.services;

import com.example.exams_backend.dto.GetInformationDTO;
import com.example.exams_backend.dto.ScoresDTO;
import com.example.exams_backend.models.Account;
import com.example.exams_backend.models.DetailQuestion;
import com.example.exams_backend.models.Result;
import com.example.exams_backend.models.Scores;
import com.example.exams_backend.repository.QuestionRepository;
import com.example.exams_backend.repository.ResultRepository;
import com.example.exams_backend.repository.ScoresRepository;
import com.example.exams_backend.responses.QuestionResponse;
import com.example.exams_backend.responses.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScoresService implements IScoresService{
    private final ScoresRepository scoresRepository;
    private final ResultService resultService;
    private final QuestionRepository questionRepository;
    private final DetailQuestionService detailQuestionService;
    private final ResultRepository resultRepository;
    private final AccountService accountService;
    @Override
    public ResponseEntity<?> createScore(ScoresDTO scoresDTO) {
        if (this.formData(scoresDTO)!=null) {
            return ResponseEntity.ok(this.scoresRepository.save(this.formData(scoresDTO)));
        }
        return ResponseEntity.ok("Can not create new score");
    }

    @Override
    public ResultResponse doneExam(GetInformationDTO getInformationDTO) {
        String username = getInformationDTO.getUsername();

        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username must not be null or empty");
        }

        List<Scores> scores = this.getLatestScores(username);
        Long total = (long) scores.size();
        Long count = 0L;

        for (Scores clone : scores) {
            DetailQuestion line = this.detailQuestionService.findQuestionById(clone.getQuestion().getId());
            if (this.comparationTwoAnswer(
                    line.getTrueAnswer().trim(),
                    (clone.getCurrentAnswer().trim()))) {
                count++;
            }
        }

        Account account = this.accountService.findAccountByUserName(username);

        if (account == null) {
            throw new RuntimeException("Account not found for username: " + username);
        }
        Result result = this.resultService.getResultByUserName(account.getUsername());
        if (result!=null) {
            Result answer = Result.builder()
                    .id(result.getId())
                    .account(account)
                    .countCorrectAnswer(count)
                    .countTotalQuestion(total)
                    .build();
            this.resultRepository.save(answer);

        } else {
            Result answer = Result.builder()
                    .account(account)
                    .countCorrectAnswer(count)
                    .countTotalQuestion(total)
                    .build();
            this.resultRepository.save(answer);

        }

        return ResultResponse.builder()
                .correctAnswer(count)
                .totalQuestion(total)
                .username(username)
                .build();
    }

    @Override
    public ResponseEntity<?> updateScore(ScoresDTO scoresDTO) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteScores(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<?> getUser(String username) {
        return null;
    }
    public Scores formData(ScoresDTO scoresDTO){
        DetailQuestion detailQuestion = this.detailQuestionService
                .findQuestionById(Long.parseLong(scoresDTO.getQuestionId()));
        Account account = this.accountService
                .findAccountByUserName(scoresDTO.getUsername());
        if (account!=null&&detailQuestion!=null) {
            return Scores.builder()
                    .account(account)
                    .question(detailQuestion)
                    .currentAnswer(scoresDTO.current_answer)
                    .build();
        }
        return null;
    }
    public List<Scores> getLatestScores(String username) {
        List<Object[]> results = scoresRepository.findLatestScoresForEachQuestion(username);
        List<Scores> list = new ArrayList<>();
        if (results == null || results.isEmpty()) {
            System.out.println("No results found.");
            return Collections.emptyList(); // Return an empty list or handle the case as needed
        }

        // Log the size and content of the results for further inspection
        System.out.println("Number of results: " + results.size());
        for (Object[] result : results) {
            System.out.println("Result: " + Arrays.toString(result));
            Scores score = new Scores();
            score.setId(Long.parseLong(result[0].toString())); // Convert Integer to Long
            score.setAccount(this.accountService.findAccountByUserName((String) result[1]));
            score.setQuestion(this.detailQuestionService.getDefaultQuestion(Long.parseLong(result[2].toString())));
            score.setCurrentAnswer((String) result[3]);
            list.add(score);
        }

        // Process results if not empty
        return list;
    }
    public boolean comparationTwoAnswer(String answer, String target) {
        // Split the input strings by comma and store the parts in sets
        Set<String> answerSet = new HashSet<>(Arrays.asList(answer.split(",")));
        Set<String> targetSet = new HashSet<>(Arrays.asList(target.split(",")));

        // Compare the sets
        return answerSet.equals(targetSet);
    }
    public DetailQuestion formData(QuestionResponse questionResponse){
        return DetailQuestion.builder()
                .questionType(questionResponse.getQuestionType())
                .img(questionResponse.getImg())
                .rtf(questionResponse.getRtf())
                .build();
    }
}
