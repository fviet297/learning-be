package com.learningapp.controller;

import com.learningapp.controller.dto.QuizSubmission;
import com.learningapp.entity.Quiz;
import com.learningapp.entity.QuizResult;
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
    public ResponseEntity<Quiz> createQuiz(@RequestBody Quiz quiz) {
        if (quiz.getQuestion() == null || quiz.getQuestion().trim().isEmpty() ||
                quiz.getOptions() == null || quiz.getCorrectAnswer() < 0 || quiz.getCorrectAnswer() > 3) {
            return ResponseEntity.badRequest().build();
        }
        Quiz savedQuiz = quizRepository.save(quiz);
        return ResponseEntity.ok(savedQuiz);
    }

    @GetMapping
    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        List<Quiz> quizzes = quizRepository.findAll();
        return ResponseEntity.ok(quizzes);
    }

    @PostMapping("/submit")
    public ResponseEntity<QuizResult> submitQuiz(@RequestBody QuizSubmission submission) {
        if (submission.getQuizId() == null || submission.getSelectedOption() < 0 || submission.getSelectedOption() > 3) {
            return ResponseEntity.badRequest().build();
        }
        Quiz quiz = quizRepository.findById(submission.getQuizId())
                .orElseThrow(() -> new RuntimeException("Quiz not found with id: " + submission.getQuizId()));
        int score = submission.getSelectedOption() == quiz.getCorrectAnswer() ? 10 : 0;

        QuizResult result = new QuizResult();
        result.setUserId(submission.getUserId());
        result.setQuizId(submission.getQuizId());
        result.setScore(score);
        QuizResult savedResult = quizResultRepository.save(result);
        return ResponseEntity.ok(savedResult);
    }
}