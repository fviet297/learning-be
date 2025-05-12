package com.learningapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class QuizDTO {

    @JsonProperty
    private String title;

    @JsonProperty
    private String description;

    @JsonProperty
    private Long studyModuleId;
} 