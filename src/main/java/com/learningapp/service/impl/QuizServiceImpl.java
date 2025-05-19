package com.learningapp.service.impl;

import com.learningapp.constants.CoreConstants;
import com.learningapp.dto.QuizSubmission;
import com.learningapp.dto.request.QuizRequest;
import com.learningapp.dto.request.QuizResultRequest;
import com.learningapp.dto.response.QuizResponse;
import com.learningapp.dto.response.QuizResultResponse;
import com.learningapp.entity.Quiz;
import com.learningapp.entity.StudyModule;
import com.learningapp.exception.NotFoundException;
import com.learningapp.mapper.QuizMapper;
import com.learningapp.repository.QuizRepository;
import com.learningapp.service.QuizResultService;
import com.learningapp.service.QuizService;
import com.learningapp.service.StudyModuleService;
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

    @Autowired
    public QuizServiceImpl(final QuizRepository quizRepository,
                           final QuizMapper quizMapper,
                           final StudyModuleService studyModuleService,
                           QuizResultService quizResultService) {
        this.quizRepository = quizRepository;
        this.quizMapper = quizMapper;
        this.studyModuleService = studyModuleService;
        this.quizResultService = quizResultService;
    }

    @Override
    public QuizResponse create(@NotNull final QuizRequest quizRequest) {
        Quiz quizEntity = quizMapper.toEntity(quizRequest);
        final StudyModule studyModule = studyModuleService.getEntityById(quizRequest.getStudyModuleId());
        quizEntity.setStudyModule(studyModule);
        quizEntity = quizRepository.save(quizEntity);
        return quizMapper.toResponse(quizEntity);
    }

    @Override
    public List<QuizResponse> getQuizzesByStudyModuleId(final String studyModuleId) {
        final List<Quiz> quizzes = quizRepository.findByStudyModuleId(studyModuleId);
        return quizMapper.toResponses(quizzes);
    }

    @Override
    public Quiz getEntityById(final String id) {
        return quizRepository.findByIdAndIsDeleteIsFalse(id).orElseThrow(
                () -> new NotFoundException(
                        String.format(
                                CoreConstants.MESSAGE_ERROR.NOT_FOUND_ENTITY,
                                Quiz.class.getSimpleName(),
                                id
                        )));
    }

    @Override
    public QuizResultResponse submitQuiz(@NotNull final QuizSubmission quizSubmission) {
        final Quiz quiz = this.getEntityById(quizSubmission.getQuizId());
        final int score = quizSubmission.getSelectedOption() == quiz.getCorrectAnswer()
                ? 10 : 0;

        final QuizResultRequest quizResultRequest = QuizResultRequest.builder()
                .quizId(quizSubmission.getQuizId())
                .userId(quizSubmission.getUserId())
                .score(score).build();
        return quizResultService.create(quizResultRequest);
    }

}
