package com.learningapp.dto.response;

import com.learningapp.dto.StudyModuleDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class StudyModuleResponse extends StudyModuleDTO {

    private String id;
    private List<FlashcardResponse> flashcards;
    private List<QuizResponse> quizzes;
}
