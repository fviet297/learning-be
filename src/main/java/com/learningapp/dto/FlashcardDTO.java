package com.learningapp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class FlashcardDTO implements Serializable {

    private String id;

    @NotBlank
    private String studyModuleId;

    @NotBlank(message = "không thể trống")
    private String content;

    private String status;
}