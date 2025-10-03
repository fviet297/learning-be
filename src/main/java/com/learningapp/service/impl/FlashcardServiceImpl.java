package com.learningapp.service.impl;

import com.learningapp.constants.Constants;
import com.learningapp.dto.ResponseData;
import com.learningapp.dto.request.FlashcardRequest;
import com.learningapp.dto.request.FlashcardRequestBulk;
import com.learningapp.dto.response.FlashcardResponse;
import com.learningapp.entity.Flashcard;
import com.learningapp.entity.StudyModule;
import com.learningapp.enums.CreationEnum;
import com.learningapp.enums.FlashcardStatus;
import com.learningapp.exception.NotFoundException;
import com.learningapp.mapper.FlashcardMapper;
import com.learningapp.repository.FlashcardRepository;
import com.learningapp.service.FlashcardService;
import com.learningapp.service.OpenRouterService;
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
    private final OpenRouterService openRouterService;

    @Autowired
    public FlashcardServiceImpl(final FlashcardRepository flashcardRepository,
                                final FlashcardMapper flashcardMapper,
                                final StudyModuleService studyModuleService,
                                final OpenRouterService openRouterService) {
        this.flashcardRepository = flashcardRepository;
        this.flashcardMapper = flashcardMapper;
        this.studyModuleService = studyModuleService;
        this.openRouterService = openRouterService;
    }

    @Override
    public List<FlashcardResponse> createBulk(@NotNull final FlashcardRequestBulk flashcardRequestBulk) {
        List<Flashcard> flashcards = flashcardMapper.toEntity(flashcardRequestBulk.getFlashcardRequests());

        final StudyModule studyModule = studyModuleService.getEntityById(flashcardRequestBulk.getStudyModuleId());

        flashcards.forEach(i -> {
            i.setStudyModule(studyModule);
            i.setStatus(FlashcardStatus.LEARN);
        });

        flashcards = flashcardRepository.saveAll(flashcards);

        return flashcardMapper.toResponse(flashcards);
    }

    @Override
    public List<FlashcardResponse> createBulkGen(@NotNull final FlashcardRequestBulk flashcardRequestBulk) {

        final FlashcardRequest generateFlashcards = openRouterService.generate(flashcardRequestBulk.getContent(), CreationEnum.FLASHCARD);

        List<Flashcard> flashcards = flashcardMapper.toEntity(List.of(generateFlashcards));

        final StudyModule studyModule = studyModuleService.getEntityById(flashcardRequestBulk.getStudyModuleId());

        flashcards.forEach(i -> {
            i.setStudyModule(studyModule);
            i.setStatus(FlashcardStatus.LEARN);
        });

        flashcards = flashcardRepository.saveAll(flashcards);

        return flashcardMapper.toResponse(flashcards);
    }


    @Override
    public FlashcardResponse update(@NotNull final FlashcardRequest flashcardRequest) {
        Flashcard flashcard = getEntityById(flashcardRequest.getId());
        final StudyModule studyModule = flashcard.getStudyModule();
        flashcard = flashcardMapper.toEntity(flashcardRequest);
        flashcard.setStudyModule(studyModule);
        return flashcardMapper.toResponse(flashcardRepository.save(flashcard));
    }

    @Override
    public FlashcardResponse random(@NotNull final String studyModuleId) {
        final List<Flashcard> flashcards = flashcardRepository.findByStatusAndStudyModuleId(FlashcardStatus.LEARN, studyModuleId);
        if (flashcards.isEmpty()) {
            return null;
        }
        final Flashcard randomFlashcard = flashcards.get(new Random().nextInt(flashcards.size()));
        return flashcardMapper.toResponse(randomFlashcard);
    }

    @Override
    public List<FlashcardResponse> getFlashcardsByModuleId(final String moduleId) {
        final List<Flashcard> flashcards = flashcardRepository.findByStudyModuleId(moduleId);
        if (flashcards.isEmpty()) {
            return null;
        }
        return flashcardMapper.toResponse(flashcards);
    }

    @Override
    @Transactional
    public ResponseData deleteFlashcard(final String id) {
        Flashcard flashcard = getEntityById(id);
        flashcard.setIsDelete(true);
        return ResponseData.builder().build();
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
                                            Constants.MESSAGE_ERROR.NOT_FOUND_ENTITY,
                                            Flashcard.class.getSimpleName(),
                                            id
                                    )));
        }
    }

}
