package com.learningapp.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuizDTO {

    private String id;

    @NotBlank
    private String question;

    @NotBlank
    private String options; // JSON array of 4 options

    @Min(value = 1)
    private int correctAnswer; // 0-3 index of correct option

}