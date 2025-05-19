package com.learningapp.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizSubmission {

    @NotBlank
    private String quizId;

    @NotBlank
    private String userId;

    @Min(value = 1)
    private int selectedOption;
}
