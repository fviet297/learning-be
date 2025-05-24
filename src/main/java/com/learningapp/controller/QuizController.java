package com.learningapp.controller;

import com.learningapp.dto.ResponseData;
import com.learningapp.dto.request.QuizRequestBulk;
import com.learningapp.dto.response.QuizResponse;
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
    public ResponseEntity<ResponseData> createQuiz(@RequestBody QuizRequestBulk quizRequestBulk) {
        final List<QuizResponse> quizResponse = quizService.create(quizRequestBulk);
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

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData> deleteQuiz(@PathVariable final String id) {
        return ResponseEntity.ok(quizService.deleteQuiz(id));
    }
}