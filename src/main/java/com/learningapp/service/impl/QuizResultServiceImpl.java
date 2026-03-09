package com.learningapp.service.impl;

import com.learningapp.dto.request.QuizResultRequest;
import com.learningapp.dto.response.QuizResultResponse;
import com.learningapp.entity.QuizResult;
import com.learningapp.mapper.QuizResultMapper;
import com.learningapp.repository.QuizResultRepository;
import com.learningapp.service.QuizResultService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizResultServiceImpl implements QuizResultService {
    private final QuizResultRepository quizResultRepository;
    private final QuizResultMapper quizResultMapper;

    @Autowired
    public QuizResultServiceImpl(final QuizResultRepository QuizResultRepository,
                           final QuizResultMapper QuizResultMapper) {
        this.quizResultRepository = QuizResultRepository;
        this.quizResultMapper = QuizResultMapper;
    }

    @Override
    public QuizResultResponse create(@NotNull final QuizResultRequest QuizResultRequest) {
        QuizResult quizResultEntity = quizResultMapper.toEntity(QuizResultRequest);
        quizResultEntity = quizResultRepository.save(quizResultEntity);
        return quizResultMapper.toResponse(quizResultEntity);
    }
}
