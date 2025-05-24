package com.learningapp.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuizRequestBulk {

    @NotBlank
    private String studyModuleId;

    @NotEmpty
    private List<QuizRequest> quizRequests;
}