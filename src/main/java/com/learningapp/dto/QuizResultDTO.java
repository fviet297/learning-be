package com.learningapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuizResultDTO {

    private String id;

    private Long userId;

    private Long quizId;

    private int score;
}
