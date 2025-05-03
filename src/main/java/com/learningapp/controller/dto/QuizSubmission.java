package com.learningapp.controller.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizSubmission {
    private Long quizId;
    private Long userId;
    private int selectedOption;
}
