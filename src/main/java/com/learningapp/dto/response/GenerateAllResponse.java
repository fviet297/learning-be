package com.learningapp.dto.response;

import java.util.List;

public record GenerateAllResponse(
        List<FlashcardResponse> flashcards,
        List<QuizResponse> quizzes
) {}
