package com.learningapp.service.impl;

import com.learningapp.dto.request.FlashcardRequest;
import com.learningapp.dto.response.FlashcardResponse;
import com.learningapp.entity.FlashcardEntity;
import com.learningapp.entity.StudyModuleEntity;
import com.learningapp.mapper.FlashcardMapper;
import com.learningapp.repository.FlashcardRepository;
import com.learningapp.repository.StudyModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class FlashcardServiceImpl {

    private final FlashcardRepository flashcardRepository;
    private final FlashcardMapper flashcardMapper;
    private final StudyModuleRepository studyModuleRepository;

    @Autowired
    public FlashcardServiceImpl(final FlashcardRepository flashcardRepository,
                                final FlashcardMapper flashcardMapper,
                                final StudyModuleRepository studyModuleRepository) {
        this.flashcardRepository = flashcardRepository;
        this.flashcardMapper = flashcardMapper;
        this.studyModuleRepository = studyModuleRepository;
    }

    public FlashcardResponse create(final FlashcardRequest flashcardRequest) {
        if(Objects.nonNull(flashcardRequest)){
            FlashcardEntity flashcardEntity = flashcardMapper.toEntity(flashcardRequest);
//            StudyModuleEntity studyModuleEntity = studyModuleRepository.getReferenceById(flashcardRequest.getStudyModuleId());
            flashcardEntity.setStudyModuleEntity(new StudyModuleEntity());
            flashcardEntity =  flashcardRepository.save(flashcardEntity);
            if(Objects.nonNull(flashcardEntity)){
                return flashcardMapper.toResponse(flashcardEntity);
            }
        }
        return null;
    }
}
