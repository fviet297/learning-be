package com.learningapp.service;

import com.learningapp.dto.request.QuizResultRequest;
import com.learningapp.dto.response.QuizResultResponse;
import jakarta.validation.constraints.NotNull;

public interface QuizResultService {
    QuizResultResponse create(@NotNull QuizResultRequest QuizResultRequest);
}
