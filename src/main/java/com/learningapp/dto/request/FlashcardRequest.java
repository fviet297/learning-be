package com.learningapp.dto.request;

import com.learningapp.dto.FlashcardDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class FlashcardRequest extends FlashcardDTO {

    @NotBlank
    private String studyModuleId;
}