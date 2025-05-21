package com.learningapp.controller;

import com.learningapp.dto.ResponseData;
import com.learningapp.dto.request.QuizResultRequest;
import com.learningapp.dto.response.QuizResultResponse;
import com.learningapp.service.QuizResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quiz-result")
public class QuizResultController {

    private final QuizResultService quizResultService;

    @Autowired
    public QuizResultController(QuizResultService quizResultService) {
        this.quizResultService = quizResultService;
    }

    @PostMapping
    public ResponseEntity<ResponseData> createQuiz(@RequestBody QuizResultRequest quizResultRequest) {
        final QuizResultResponse quizResponse = quizResultService.create(quizResultRequest);
        return ResponseEntity.ok(ResponseData.builder().data(quizResponse).build());
    }

}