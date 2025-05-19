package com.learningapp.mapper;

import com.learningapp.dto.request.QuizResultRequest;
import com.learningapp.dto.response.QuizResultResponse;
import com.learningapp.entity.QuizResult;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuizResultMapper {

    QuizResultResponse toResponse(QuizResult quizResult);

    QuizResult toEntity(QuizResultRequest quizResultRequest);
}