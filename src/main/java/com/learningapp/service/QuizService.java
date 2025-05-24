package com.learningapp.service;

import com.learningapp.dto.ResponseData;
import com.learningapp.dto.request.QuizRequestBulk;
import com.learningapp.dto.response.QuizResponse;
import com.learningapp.entity.Quiz;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface QuizService {
    List<QuizResponse> create(@NotNull QuizRequestBulk quizRequestBulk);

    List<QuizResponse> getQuizzesByStudyModuleId(String studyModuleId);

    @Transactional
    ResponseData deleteQuiz(@NotBlank String id);

    Quiz getEntityById(String id);
}
