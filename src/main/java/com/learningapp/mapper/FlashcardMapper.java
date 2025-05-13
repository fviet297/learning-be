package com.learningapp.mapper;

import com.learningapp.dto.request.FlashcardRequest;
import com.learningapp.dto.response.FlashcardResponse;
import com.learningapp.entity.FlashcardEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FlashcardMapper {

    FlashcardResponse toResponse(FlashcardEntity flashcardEntity);
    FlashcardEntity toEntity(FlashcardRequest flashcardRequest);
}