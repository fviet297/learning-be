package com.learningapp.mapper;

import com.learningapp.dto.request.QuizRequest;
import com.learningapp.dto.response.QuizResponse;
import com.learningapp.entity.Quiz;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuizMapper {

    QuizResponse toResponse(Quiz quiz);

    Quiz toEntity(QuizRequest quizRequest);

    List<QuizResponse> toResponses(List<Quiz> quizzes);

    List<Quiz> toEntity(List<QuizRequest> quizRequests);
}