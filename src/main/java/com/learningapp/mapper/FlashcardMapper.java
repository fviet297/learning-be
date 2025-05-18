package com.learningapp.mapper;

import com.learningapp.dto.request.FlashcardRequest;
import com.learningapp.dto.response.FlashcardResponse;
import com.learningapp.entity.Flashcard;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FlashcardMapper {

    FlashcardResponse toResponse(Flashcard flashcard);

    Flashcard toEntity(FlashcardRequest flashcardRequest);
}