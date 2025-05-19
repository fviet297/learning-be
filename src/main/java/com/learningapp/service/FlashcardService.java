package com.learningapp.service;

import com.learningapp.dto.request.FlashcardRequest;
import com.learningapp.dto.response.FlashcardResponse;
import com.learningapp.entity.Flashcard;
import com.learningapp.enums.FlashcardStatus;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public interface FlashcardService {
    FlashcardResponse create(FlashcardRequest flashcardRequest);

    FlashcardResponse random(@NotNull String studyModuleId);

    @Transactional
    FlashcardResponse updateFlashcardStatus(@NotBlank String id, FlashcardStatus status);

    Flashcard getEntityById(@NotBlank String id);
}
