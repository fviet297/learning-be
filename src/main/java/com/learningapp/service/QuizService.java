package com.learningapp.service;

import com.learningapp.dto.QuizSubmission;
import com.learningapp.dto.request.QuizRequest;
import com.learningapp.dto.response.QuizResponse;
import com.learningapp.dto.response.QuizResultResponse;
import com.learningapp.entity.Quiz;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface QuizService {
    QuizResponse create(@NotNull QuizRequest quizRequest);

    List<QuizResponse> getQuizzesByStudyModuleId(String studyModuleId);

    Quiz getEntityById(String id);

    QuizResultResponse submitQuiz(@NotNull QuizSubmission quizSubmission);
}
