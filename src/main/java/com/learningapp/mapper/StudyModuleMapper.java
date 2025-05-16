package com.learningapp.mapper;

import com.learningapp.dto.request.StudyModuleRequest;
import com.learningapp.dto.response.StudyModuleResponse;
import com.learningapp.entity.StudyModuleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {FlashcardMapper.class})
public interface StudyModuleMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "flashcards", source = "flashcards")
    StudyModuleResponse toResponse(StudyModuleEntity studyModuleEntity);

    @Mapping(target = "description", source = "description")
    @Mapping(target = "name", source = "name")
    StudyModuleEntity toEntity(StudyModuleRequest studyModuleRequest);
}