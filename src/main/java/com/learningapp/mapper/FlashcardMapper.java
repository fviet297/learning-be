package com.learningapp.mapper;

import com.learningapp.dto.request.FlashcardRequest;
import com.learningapp.dto.response.FlashcardResponse;
import com.learningapp.entity.Flashcard;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FlashcardMapper {

    FlashcardResponse toResponse(Flashcard flashcard);

    List<FlashcardResponse> toResponse(List<Flashcard> flashcards);

    Flashcard toEntity(FlashcardRequest flashcardRequest);

    List<Flashcard> toEntity(List<FlashcardRequest> flashcardRequest);

}