package com.learningapp.service;

import com.learningapp.dto.request.FlashcardRequest;
import com.learningapp.enums.CreationEnum;

public interface OpenRouterService {
    FlashcardRequest generate(String content, CreationEnum creationEnum);
}
