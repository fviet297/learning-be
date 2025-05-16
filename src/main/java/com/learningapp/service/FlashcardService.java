package com.learningapp.service;

import com.learningapp.dto.request.FlashcardRequest;
import com.learningapp.dto.response.FlashcardResponse;

public interface FlashcardService {
    FlashcardResponse create(FlashcardRequest flashcardRequest);
}
