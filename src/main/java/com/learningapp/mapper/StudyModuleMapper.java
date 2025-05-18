package com.learningapp.mapper;

import com.learningapp.dto.request.StudyModuleRequest;
import com.learningapp.dto.response.StudyModuleResponse;
import com.learningapp.entity.StudyModule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {FlashcardMapper.class})
public interface StudyModuleMapper {

    StudyModuleResponse toResponse(StudyModule studyModule);

    StudyModule toEntity(StudyModuleRequest studyModuleRequest);

    @Mapping(source = "flashcards",target = "flashcards",ignore = true)
    @Mapping(source = "quizzes",target = "quizzes",ignore = true)
    List<StudyModuleResponse> toResponseList(List<StudyModule> entities);
}