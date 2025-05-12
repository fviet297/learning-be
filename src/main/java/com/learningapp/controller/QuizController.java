package com.learningapp.controller;

import com.learningapp.controller.dto.QuizSubmission;
import com.learningapp.entity.QuizEntity;
import com.learningapp.entity.QuizResultEntity;
import com.learningapp.repository.QuizRepository;
import com.learningapp.repository.QuizResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    private final QuizRepository quizRepository;
    private final QuizResultRepository quizResultRepository;

    @Autowired
    public QuizController(QuizRepository quizRepository, QuizResultRepository quizResultRepository) {
        this.quizRepository = quizRepository;
        this.quizResultRepository = quizResultRepository;
    }

    @PostMapping
    public ResponseEntity<QuizEntity> createQuiz(@RequestBody QuizEntity quizEntity) {
        if (quizEntity.getQuestion() == null || quizEntity.getQuestion().trim().isEmpty() ||
                quizEntity.getOptions() == null || quizEntity.getCorrectAnswer() < 0 || quizEntity.getCorrectAnswer() > 3) {
            return ResponseEntity.badRequest().build();
        }
        QuizEntity savedQuizEntity = quizRepository.save(quizEntity);
        return ResponseEntity.ok(savedQuizEntity);
    }

    @GetMapping
    public ResponseEntity<List<QuizEntity>> getAllQuizzes() {
        List<QuizEntity> quizEntities = quizRepository.findAll();
        return ResponseEntity.ok(quizEntities);
    }

    @PostMapping("/submit")
    public ResponseEntity<QuizResultEntity> submitQuiz(@RequestBody QuizSubmission submission) {
        if (submission.getQuizId() == null || submission.getSelectedOption() < 0 || submission.getSelectedOption() > 3) {
            return ResponseEntity.badRequest().build();
        }
        QuizEntity quizEntity = quizRepository.findById(submission.getQuizId())
                .orElseThrow(() -> new RuntimeException("Quiz not found with id: " + submission.getQuizId()));
        int score = submission.getSelectedOption() == quizEntity.getCorrectAnswer() ? 10 : 0;

        QuizResultEntity result = new QuizResultEntity();
        result.setUserId(submission.getUserId());
        result.setQuizId(submission.getQuizId());
        result.setScore(score);
        QuizResultEntity savedResult = quizResultRepository.save(result);
        return ResponseEntity.ok(savedResult);
    }
}