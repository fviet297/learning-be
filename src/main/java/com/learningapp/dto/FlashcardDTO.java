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
    private String question;

    @NotBlank
    private String answer;

    private String status;
}