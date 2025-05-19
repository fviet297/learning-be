package com.learningapp.mapper;

import com.learningapp.dto.request.StudyModuleRequest;
import com.learningapp.dto.response.StudyModuleResponse;
import com.learningapp.entity.StudyModule;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {FlashcardMapper.class})
public interface StudyModuleMapper {

    StudyModule toEntity(StudyModuleRequest studyModuleRequest);
    StudyModuleResponse toResponse(StudyModule studyModule);
}