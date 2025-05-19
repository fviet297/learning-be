package com.learningapp.controller;

import com.learningapp.dto.QuizSubmission;
import com.learningapp.dto.ResponseData;
import com.learningapp.dto.request.QuizRequest;
import com.learningapp.dto.response.QuizResponse;
import com.learningapp.dto.response.QuizResultResponse;
import com.learningapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quizzes")
public class QuizController {

    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping
    public ResponseEntity<ResponseData> createQuiz(@RequestBody QuizRequest quizRequest) {
        final QuizResponse quizResponse = quizService.create(quizRequest);
        return ResponseEntity.ok(ResponseData.builder().data(quizResponse).build());
    }

    @GetMapping("/{studyModuleId}")
    public ResponseEntity<ResponseData> getAllQuizzesByStudyModuleId(@PathVariable final String studyModuleId) {
        final List<QuizResponse> quizResponses = quizService.getQuizzesByStudyModuleId(studyModuleId);
        if (quizResponses.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ResponseData.builder().data(quizResponses).build());
    }

    @PostMapping("/submit")
    public ResponseEntity<ResponseData> submitQuiz(@RequestBody QuizSubmission submission) {
        final QuizResultResponse quizResultResponse = quizService.submitQuiz(submission);
        return ResponseEntity.ok(ResponseData.builder().data(quizResultResponse).build());
    }
}