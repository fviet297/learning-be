package com.learningapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class FlashcardDTO {

    @JsonProperty
    private String content;

    @JsonProperty
    private String status;

    @JsonProperty
    private String studyModuleId;
} 