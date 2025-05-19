package com.learningapp.service.impl;

import com.learningapp.constants.CoreConstants;
import com.learningapp.dto.request.FlashcardRequest;
import com.learningapp.dto.response.FlashcardResponse;
import com.learningapp.entity.Flashcard;
import com.learningapp.entity.StudyModule;
import com.learningapp.enums.FlashcardStatus;
import com.learningapp.exception.NotFoundException;
import com.learningapp.mapper.FlashcardMapper;
import com.learningapp.repository.FlashcardRepository;
import com.learningapp.service.FlashcardService;
import com.learningapp.service.StudyModuleService;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

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
    public FlashcardResponse create(@NotNull final FlashcardRequest flashcardRequest) {
        Flashcard flashcard = flashcardMapper.toEntity(flashcardRequest);

        final StudyModule studyModule = studyModuleService.getEntityById(flashcardRequest.getStudyModuleId());
        flashcard.setStudyModule(studyModule);
        flashcard.setStatus(FlashcardStatus.LEARN);
        flashcard = flashcardRepository.save(flashcard);
        return flashcardMapper.toResponse(flashcard);
    }

    @Override
    public FlashcardResponse random(@NotNull final String studyModuleId) {
        final List<Flashcard> flashcard = flashcardRepository.findByStatusAndStudyModuleId(FlashcardStatus.LEARN, studyModuleId);
        if (flashcard.isEmpty()) {
            return null;
        }
        final Flashcard randomFlashcard = flashcard.get(new Random().nextInt(flashcard.size()));
        return flashcardMapper.toResponse(randomFlashcard);
    }

    @Transactional
    @Override
    public FlashcardResponse updateFlashcardStatus(@NotBlank final String id, final FlashcardStatus status) {
        final Flashcard flashcard = this.getEntityById(id);
        flashcard.setStatus(status);
        return flashcardMapper.toResponse(flashcard);
    }

    @Override
    public Flashcard getEntityById(@NotBlank final String id) {
        {
            return flashcardRepository
                    .findByIdAndIsDeleteFalse(id)
                    .orElseThrow(
                            () -> new NotFoundException(
                                    String.format(
                                            CoreConstants.MESSAGE_ERROR.NOT_FOUND_ENTITY,
                                            StudyModule.class.getSimpleName(),
                                            id
                                    )));
        }
    }

}
