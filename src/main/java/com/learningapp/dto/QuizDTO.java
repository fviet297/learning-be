package com.learningapp.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class QuizDTO {

    private String id;

    @NotBlank
    private String question;

    @NotBlank
    private List<String> options; // JSON array of 4 options

    @Min(value = 0)
    private int correctAnswer; // 0-3 index of correct option

}
