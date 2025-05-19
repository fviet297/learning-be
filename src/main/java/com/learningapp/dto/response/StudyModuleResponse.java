package com.learningapp.dto.response;

import com.learningapp.dto.StudyModuleDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class StudyModuleResponse extends StudyModuleDTO {

    private List<FlashcardResponse> flashcards;
    private List<QuizResponse> quizzes;
}
