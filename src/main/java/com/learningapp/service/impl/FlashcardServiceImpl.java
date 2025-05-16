package com.learningapp.service.impl;

import com.learningapp.dto.request.FlashcardRequest;
import com.learningapp.dto.response.FlashcardResponse;
import com.learningapp.entity.FlashcardEntity;
import com.learningapp.entity.StudyModuleEntity;
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
            FlashcardEntity flashcardEntity = flashcardMapper.toEntity(flashcardRequest);
            StudyModuleEntity studyModuleEntity = studyModuleService.findById(flashcardRequest.getStudyModuleId());
            flashcardEntity.setStudyModuleEntity(studyModuleEntity);
            flashcardEntity.setStatus("LEARN");
            flashcardEntity =  flashcardRepository.save(flashcardEntity);
            if(Objects.nonNull(flashcardEntity)){
                return flashcardMapper.toResponse(flashcardEntity);
            }
        }
        return null;
    }
}
