package com.learningapp.service.impl;

import com.learningapp.dto.request.FlashcardRequest;
import com.learningapp.dto.response.FlashcardResponse;
import com.learningapp.entity.Flashcard;
import com.learningapp.entity.StudyModule;
import com.learningapp.mapper.FlashcardMapper;
import com.learningapp.repository.FlashcardRepository;
import com.learningapp.service.FlashcardService;
import com.learningapp.service.StudyModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class FlashcardServiceImpl implements FlashcardService {

    private final FlashcardRepository flashcardRepository;
    private final FlashcardMapper flashcardMapper;
    private final StudyModuleService studyModuleService;

    @Autowired
    public FlashcardServiceImpl(final FlashcardRepository flashcardRepository,
                                final FlashcardMapper flashcardMapper,
                                final StudyModuleService studyModuleService) {
        this.flashcardRepository = flashcardRepository;
        this.flashcardMapper = flashcardMapper;
        this.studyModuleService = studyModuleService;
    }

    @Override
    public FlashcardResponse create(final FlashcardRequest flashcardRequest) {
        if(Objects.nonNull(flashcardRequest)){
            Flashcard flashcard = flashcardMapper.toEntity(flashcardRequest);
            StudyModule studyModule = studyModuleService.getEntityById(flashcardRequest.getStudyModuleId());
            flashcard.setStudyModule(studyModule);
            flashcard.setStatus("LEARN");
            flashcard =  flashcardRepository.save(flashcard);
            if(Objects.nonNull(flashcard)){
                return flashcardMapper.toResponse(flashcard);
            }
        }
        return null;
    }
}
