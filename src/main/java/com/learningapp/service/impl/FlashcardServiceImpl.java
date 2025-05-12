package com.learningapp.service.impl;

import com.learningapp.dto.request.FlashcardRequest;
import com.learningapp.dto.response.FlashcardResponse;
import com.learningapp.repository.FlashcardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlashcardServiceImpl {

    private final FlashcardRepository flashcardRepositoryy;

    @Autowired
    public FlashcardServiceImpl(final FlashcardRepository flashcardRepositoryy) {
        this.flashcardRepositoryy = flashcardRepositoryy;
    }

    public FlashcardResponse create(final FlashcardRequest flashcardRequest) {

    }
}
