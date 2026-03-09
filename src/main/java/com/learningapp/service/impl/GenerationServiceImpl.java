package com.learningapp.service.impl;

import com.learningapp.dto.APIResponseContent;
import com.learningapp.dto.request.FlashcardRequest;
import com.learningapp.dto.request.QuizRequest;
import com.learningapp.dto.request.StudyModuleRequest;
import com.learningapp.dto.response.FlashcardResponse;
import com.learningapp.dto.response.QuizResponse;
import com.learningapp.dto.response.StudyModuleResponse;
import com.learningapp.entity.Flashcard;
import com.learningapp.entity.Quiz;
import com.learningapp.entity.StudyModule;
import com.learningapp.enums.FlashcardStatus;
import com.learningapp.mapper.FlashcardMapper;
import com.learningapp.mapper.QuizMapper;
import com.learningapp.repository.FlashcardRepository;
import com.learningapp.repository.QuizRepository;
import com.learningapp.service.GenerationService;
import com.learningapp.service.OpenRouterService;
import com.learningapp.service.StudyModuleService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class GenerationServiceImpl implements GenerationService {

    private final OpenRouterService openRouterService;
    private final StudyModuleService studyModuleService;
    private final FlashcardMapper flashcardMapper;
    private final QuizMapper quizMapper;
    private final FlashcardRepository flashcardRepository;
    private final QuizRepository quizRepository;

    @Autowired
    public GenerationServiceImpl(OpenRouterService openRouterService,
                                 StudyModuleService studyModuleService,
                                 FlashcardMapper flashcardMapper,
                                 QuizMapper quizMapper,
                                 FlashcardRepository flashcardRepository,
                                 QuizRepository quizRepository) {
        this.openRouterService = openRouterService;
        this.studyModuleService = studyModuleService;
        this.flashcardMapper = flashcardMapper;
        this.quizMapper = quizMapper;
        this.flashcardRepository = flashcardRepository;
        this.quizRepository = quizRepository;
    }

    @Override
    @Transactional
    public StudyModuleResponse generateAndSave(StudyModuleRequest request) {

        APIResponseContent apiContent = openRouterService.generate(request.getContent());

        List<FlashcardRequest> flashcardRequests = apiContent != null ? apiContent.flashcardRequestList() : Collections.emptyList();
        List<QuizRequest> quizRequests = apiContent != null ? apiContent.quizRequestList() : Collections.emptyList();

        StudyModule studyModule = studyModuleService.getEntityById(request.getId());

        List<FlashcardResponse> flashcardResponses;
        if (flashcardRequests != null && !flashcardRequests.isEmpty()) {
            List<Flashcard> flashcards = flashcardMapper.toEntity(flashcardRequests);
            flashcards.forEach(f -> {
                f.setId(null); // Ensure new entity
                f.setStudyModule(studyModule);
                f.setStatus(FlashcardStatus.LEARN);
                f.setIsDelete(false);
            });
            flashcards = flashcardRepository.saveAll(flashcards);
            flashcardResponses = flashcardMapper.toResponse(flashcards);
        } else {
            flashcardResponses = Collections.emptyList();
        }

        List<QuizResponse> quizResponses;
        if (quizRequests != null && !quizRequests.isEmpty()) {
            List<Quiz> quizzes = quizMapper.toEntity(quizRequests);
            quizzes.forEach(q -> {
                q.setId(null); // Ensure new entity
                q.setStudyModule(studyModule);
                q.setIsDelete(false);
            });
            quizzes = quizRepository.saveAll(quizzes);
            quizResponses = quizMapper.toResponses(quizzes);
        } else {
            quizResponses = Collections.emptyList();
        }

        return new StudyModuleResponse(flashcardResponses, quizResponses);
    }
}
