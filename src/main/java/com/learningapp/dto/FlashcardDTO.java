package com.learningapp.dto;

import com.learningapp.enums.FlashcardStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FlashcardDTO implements Serializable {

    private String id;

    @NotBlank
    private String question;

    @NotBlank
    private String answer;

    private FlashcardStatus status;
}