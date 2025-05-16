package com.learningapp.mapper;

import com.learningapp.dto.request.FlashcardRequest;
import com.learningapp.dto.response.FlashcardResponse;
import com.learningapp.entity.FlashcardEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FlashcardMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "content", source = "content")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "studyModuleId", source = "studyModuleEntity.id")
    FlashcardResponse toResponse(FlashcardEntity flashcardEntity);

    @Mapping(target = "content", source = "content")
    @Mapping(target = "status", source = "status")
    FlashcardEntity toEntity(FlashcardRequest flashcardRequest);
}