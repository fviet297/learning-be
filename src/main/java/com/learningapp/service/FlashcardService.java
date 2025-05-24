package com.learningapp.service;

import com.learningapp.dto.ResponseData;
import com.learningapp.dto.request.FlashcardRequestBulk;
import com.learningapp.dto.response.FlashcardResponse;
import com.learningapp.entity.Flashcard;
import com.learningapp.enums.FlashcardStatus;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface FlashcardService {

    List<FlashcardResponse> createBulk(@NotNull FlashcardRequestBulk flashcardRequestBulk);

    FlashcardResponse random(@NotNull String studyModuleId);

    List<FlashcardResponse> getFlashcardsByModuleId(String moduleId);

    ResponseData deleteFlashcard(String id);

    @Transactional
    FlashcardResponse updateFlashcardStatus(@NotBlank String id, FlashcardStatus status);

    Flashcard getEntityById(@NotBlank String id);
}
