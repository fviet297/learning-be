package com.learningapp.service.impl;

import com.learningapp.constants.Constants;
import com.learningapp.dto.ResponseData;
import com.learningapp.dto.request.QuizRequest;
import com.learningapp.dto.request.QuizRequestBulk;
import com.learningapp.dto.response.QuizResponse;
import com.learningapp.entity.Quiz;
import com.learningapp.entity.StudyModule;
import com.learningapp.exception.NotFoundException;
import com.learningapp.mapper.QuizMapper;
import com.learningapp.repository.QuizRepository;
import com.learningapp.service.OpenRouterService;
import com.learningapp.service.QuizResultService;
import com.learningapp.service.QuizService;
import com.learningapp.service.StudyModuleService;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {
    private final QuizRepository quizRepository;
    private final QuizMapper quizMapper;
    private final StudyModuleService studyModuleService;
    private final QuizResultService quizResultService;
    private final OpenRouterService openRouterService;

    @Autowired
    public QuizServiceImpl(final QuizRepository quizRepository,
                           final QuizMapper quizMapper,
                           final StudyModuleService studyModuleService,
                           QuizResultService quizResultService,
                           final OpenRouterService openRouterService) {
        this.quizRepository = quizRepository;
        this.quizMapper = quizMapper;
        this.studyModuleService = studyModuleService;
        this.quizResultService = quizResultService;
        this.openRouterService = openRouterService;
    }

    @Override
    public List<QuizResponse> create(@NotNull final QuizRequestBulk quizRequestBulk) {

        List<Quiz> quizzes = quizMapper.toEntity(quizRequestBulk.getQuizRequests());
        final StudyModule studyModule = studyModuleService.getEntityById(quizRequestBulk.getStudyModuleId());

        quizzes.forEach(quiz -> {
                    quiz.setStudyModule(studyModule);
                }
        );
        quizzes = quizRepository.saveAll(quizzes);
        return quizMapper.toResponses(quizzes);
    }

    @Override
    public List<QuizResponse> createGen(@NotNull final QuizRequestBulk quizRequestBulk) {

        final List<QuizRequest> generateQuizzes = openRouterService.generate(quizRequestBulk.getContent()).quizRequestList();

        List<Quiz> quizzes = quizMapper.toEntity(generateQuizzes);
        final StudyModule studyModule = studyModuleService.getEntityById(quizRequestBulk.getStudyModuleId());

        quizzes.forEach(quiz -> {
                    quiz.setStudyModule(studyModule);
                }
        );
        quizzes = quizRepository.saveAll(quizzes);
        return quizMapper.toResponses(quizzes);
    }

    @Override
    public List<QuizResponse> getQuizzesByStudyModuleId(final String studyModuleId) {
        final List<Quiz> quizzes = quizRepository.findByStudyModuleId(studyModuleId);
        return quizMapper.toResponses(quizzes);
    }

    @Transactional
    @Override
    public ResponseData deleteQuiz(final String id) {
        final Quiz quiz = getEntityById(id);
        quiz.setIsDelete(true);
        return ResponseData.builder().build();
    }

    @Override
    public Quiz getEntityById(final String id) {
        return quizRepository.findByIdAndIsDeleteIsFalse(id).orElseThrow(
                () -> new NotFoundException(
                        String.format(
                                Constants.MESSAGE_ERROR.NOT_FOUND_ENTITY,
                                Quiz.class.getSimpleName(),
                                id
                        )));
    }

}
