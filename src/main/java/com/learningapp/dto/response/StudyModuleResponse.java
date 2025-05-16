package com.learningapp.dto.response;

import com.learningapp.dto.StudyModuleDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@AllArgsConstructor
@Setter
@Getter
public class StudyModuleResponse extends StudyModuleDTO {

    private List<FlashcardResponse> flashcards;
    private List<QuizResponse> quizzes;
}
