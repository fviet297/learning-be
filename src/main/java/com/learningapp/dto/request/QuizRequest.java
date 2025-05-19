package com.learningapp.dto.request;

import com.learningapp.dto.QuizDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuizRequest extends QuizDTO {

    @NotBlank
    private String studyModuleId;
} 