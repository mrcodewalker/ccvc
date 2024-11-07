package com.example.exams_backend.services;

import com.example.exams_backend.dto.DetailOptionDTO;
import com.example.exams_backend.dto.DetailQuestionDTO;
import com.example.exams_backend.models.DetailOption;
import com.example.exams_backend.models.DetailQuestion;
import com.example.exams_backend.repository.OptionRepository;
import com.example.exams_backend.repository.QuestionRepository;
import com.example.exams_backend.responses.CompleteExamResponse;
import com.example.exams_backend.responses.ErrorDialogResponse;
import com.example.exams_backend.responses.ListQuestionsResponse;
import com.example.exams_backend.responses.QuestionResponse;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DetailQuestionService implements IDetailQuestionService{
    private final QuestionRepository questionRepository;
    private final OptionRepository optionRepository;
    private final Random random = new Random();
    private final Map<String, String> result = new HashMap<>();
    @Override
    public ResponseEntity<?> createQuestion() {
        for (int j=0;j<200;j++) {
            Faker faker = new Faker();

            // Generate fake data for the question
            DetailQuestion question = DetailQuestion.builder()
                    .currentAnswer(faker.lorem().word())
                    .ghiChu(faker.lorem().sentence())
                    .idCauTruc(UUID.randomUUID().toString())
                    .img(faker.internet().url())
                    .modify(true)
                    .trueAnswer("A")
                    .noiDung(faker.lorem().paragraph())
                    .rtf(faker.lorem().paragraph())
                    .suDung(faker.lorem().sentence())
                    .questionType("4")
                    .build();

            // Save the question
            DetailQuestion savedQuestion = questionRepository.save(question);

            // Create and save options
            List<DetailOption> options = new ArrayList<>();
            for (int i = 0; i < 4; i++) {  // Assuming 4 options per question
                DetailOption option = DetailOption.builder()
                        .idCauHoi(savedQuestion.getId() + "")
                        .img(faker.internet().url())
                        .noiDung(faker.lorem().sentence())
                        .rtf(faker.lorem().paragraph())
                        .detailQuestion(savedQuestion)
                        .build();
                options.add(option);
            }
            optionRepository.saveAll(options);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Question and options created successfully");
    }

    @Override
    public ResponseEntity<?> updateQuestion(DetailQuestionDTO detailQuestionDTO) {
        return null;
    }

    @Override
    public ResponseEntity<?> getAnswer(DetailQuestionDTO detailQuestionDTO) {
        // Tiếp tục xử lý sau khi đã trễ
        Optional<DetailQuestion> question = this.questionRepository.findById(detailQuestionDTO.getId());
        if (question.isPresent()){
            if (detailQuestionDTO.getCurrentAnswer().equals(
                    question.get().getTrueAnswer()
            )){
                result.put(detailQuestionDTO.getId()+"", "1");
            } else {
                result.put(detailQuestionDTO.getId()+"", "0");
            }
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(ErrorDialogResponse.builder()
                        .title("Lỗi đường truyền mạng")
                        .message("Hãy gọi cho giáo viên")
                        .error(false)
                .build());
    }

    @Override
    public ResponseEntity<?> deleteQuestion(Long id) {
        return null;
    }

    @Override
    public QuestionResponse getQuestion(Long id) {
        // Retrieve the question by ID
        DetailQuestion detailQuestion = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        // Retrieve the options associated with the question
        List<DetailOption> options = optionRepository.findByDetailQuestionId(id);

        // Map the options to DetailOptionDTOs
        List<DetailOptionDTO> optionDTOs = options.stream()
                .map(option -> DetailOptionDTO.builder()
                        .idCauHoi(option.getIdCauHoi())
                        .img(option.getImg())
                        .noiDung(option.getNoiDung())
                        .rtf(option.getRtf())
                        .build())
                .collect(Collectors.toList());

        // Create the response object
        QuestionResponse response = QuestionResponse.builder()
                .currentAnswer(detailQuestion.getCurrentAnswer())
                .ghiChu(detailQuestion.getGhiChu())
                .idCauTruc(detailQuestion.getIdCauTruc())
                .img(detailQuestion.getImg())
                .modify(true)
                .noiDung(detailQuestion.getNoiDung())
                .rtf(detailQuestion.getRtf())
                .id(detailQuestion.getId())
                .suDung(detailQuestion.getSuDung()+"")
                .questionType(detailQuestion.getQuestionType())
                .detailOptionDTO( optionDTOs)  // Assuming you add this field in QuestionResponse as a list
                .build();

        // Return the response
//        try {
//            // Add a delay of 1 second (1000 milliseconds)
//            int delay = 100 + random.nextInt(401); // 100 to 500 milliseconds
//            Thread.sleep(delay);
//        } catch (InterruptedException e) {
//            // Handle the interruption properly
//            Thread.currentThread().interrupt(); // Restore the interrupted status
//            // Optionally, log the interruption or handle it as needed
//        }
        return response;
    }

    @Override
    public List<QuestionResponse> collectAll() throws Exception {
        Pageable pageable = PageRequest.of(0, 50);
        Page<DetailQuestion> pageResult = this.questionRepository.findAll(pageable);
        List<QuestionResponse> result = new ArrayList<>();

        // Lặp qua từng đối tượng DetailQuestion trong danh sách
        for (DetailQuestion clone : pageResult.getContent()) {
            // Lấy các tùy chọn (options) dựa trên ID của câu hỏi
            List<DetailOption> options = optionRepository.findByDetailQuestionId(clone.getId());

            // Ánh xạ các tùy chọn sang DetailOptionDTOs
            List<DetailOptionDTO> optionDTOs = options.stream()
                    .map(option -> DetailOptionDTO.builder()
                            .idCauHoi(option.getIdCauHoi())
                            .img(option.getImg())
                            .noiDung(option.getNoiDung())
                            .rtf(option.getRtf())
                            .build())
                    .collect(Collectors.toList());

            // Tạo đối tượng QuestionResponse
            QuestionResponse response = QuestionResponse.builder()
                    .currentAnswer(clone.getCurrentAnswer())
                    .ghiChu(clone.getGhiChu())
                    .idCauTruc(clone.getIdCauTruc())
                    .img(clone.getImg())
                    .modify(true)
                    .noiDung(clone.getNoiDung())
                    .rtf(clone.getRtf())
                    .id(clone.getId())
                    .suDung(String.valueOf(clone.getSuDung())) // Chuyển đổi sang String nếu cần
                    .questionType(clone.getQuestionType())
                    .detailOptionDTO(optionDTOs) // Danh sách các tùy chọn
                    .build();

            // Thêm vào danh sách kết quả
            result.add(response);
        }

        // Trả về danh sách các QuestionResponse
        return result;
    }

    @Override
    public QuestionResponse collectOneQuestion() throws Exception {
        Pageable pageable = PageRequest.of(0, 1);
        Page<DetailQuestion> pageResult = this.questionRepository.findAll(pageable);
        List<QuestionResponse> result = new ArrayList<>();

        // Lặp qua từng đối tượng DetailQuestion trong danh sách
        for (DetailQuestion clone : pageResult.getContent()) {
            // Lấy các tùy chọn (options) dựa trên ID của câu hỏi
            List<DetailOption> options = optionRepository.findByDetailQuestionId(clone.getId());

            // Ánh xạ các tùy chọn sang DetailOptionDTOs
            List<DetailOptionDTO> optionDTOs = options.stream()
                    .map(option -> DetailOptionDTO.builder()
                            .idCauHoi(option.getIdCauHoi())
                            .img(option.getImg())
                            .noiDung(option.getNoiDung())
                            .rtf(option.getRtf())
                            .build())
                    .collect(Collectors.toList());

            // Tạo đối tượng QuestionResponse
            QuestionResponse response = QuestionResponse.builder()
                    .currentAnswer(clone.getCurrentAnswer())
                    .ghiChu(clone.getGhiChu())
                    .idCauTruc(clone.getIdCauTruc())
                    .img(clone.getImg())
                    .modify(true)
                    .noiDung(clone.getNoiDung())
                    .rtf(clone.getRtf())
                    .id(clone.getId())
                    .suDung(String.valueOf(clone.getSuDung())) // Chuyển đổi sang String nếu cần
                    .questionType(clone.getQuestionType())
                    .detailOptionDTO(optionDTOs) // Danh sách các tùy chọn
                    .build();

            // Thêm vào danh sách kết quả
            result.add(response);
        }

        // Trả về danh sách các QuestionResponse
        return result.get(0);
    }

    @Override
    public ResponseEntity<?> completeExam() {
        long trueCount = 0;
        long size = result.size();
        for (String line: result.values()){
            if (line.equals("1")){
                trueCount++;
            }
        }
        result.clear();
        return ResponseEntity.ok(
                CompleteExamResponse.builder()
                        .status("200")
                        .correctAnswer(trueCount+"")
                        .totalAnswered(size+"")
                        .build()
        );
    }

    @Override
    public ListQuestionsResponse findAll(int page, int size) throws Exception {
        Pageable pageable = PageRequest.of(page, size);
        Page<DetailQuestion> pageResult = this.questionRepository.findAll(pageable);
        List<QuestionResponse> result = new ArrayList<>();

        // Lặp qua từng đối tượng DetailQuestion trong danh sách
        for (DetailQuestion clone : pageResult.getContent()) {
            // Lấy các tùy chọn (options) dựa trên ID của câu hỏi
            List<DetailOption> options = optionRepository.findByDetailQuestionId(clone.getId());

            // Ánh xạ các tùy chọn sang DetailOptionDTOs
            List<DetailOptionDTO> optionDTOs = options.stream()
                    .map(option -> DetailOptionDTO.builder()
                            .idCauHoi(option.getIdCauHoi())
                            .img(option.getImg())
                            .noiDung(option.getNoiDung())
                            .rtf(option.getRtf())
                            .build())
                    .collect(Collectors.toList());

            // Tạo đối tượng QuestionResponse
            QuestionResponse response = QuestionResponse.builder()
                    .currentAnswer(clone.getCurrentAnswer())
                    .ghiChu(clone.getGhiChu())
                    .idCauTruc(clone.getIdCauTruc())
                    .img(clone.getImg())
                    .modify(true)
                    .noiDung(clone.getNoiDung())
                    .rtf(clone.getRtf())
                    .id(clone.getId())
                    .suDung(String.valueOf(clone.getSuDung())) // Chuyển đổi sang String nếu cần
                    .questionType(clone.getQuestionType())
                    .detailOptionDTO(optionDTOs) // Danh sách các tùy chọn
                    .build();

            // Thêm vào danh sách kết quả
            result.add(response);
        }

        // Trả về danh sách các QuestionResponse
        return ListQuestionsResponse.builder()
                .questionResponses(result)
                .totalPages(pageResult.getTotalPages())
                .build();
    }

    @Override
    public DetailQuestion findQuestionById(Long id) {
        return this.questionRepository.findById(id).orElse(null);
    }
    public DetailQuestion getDefaultQuestion(Long id){
        return questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));
    }
}
