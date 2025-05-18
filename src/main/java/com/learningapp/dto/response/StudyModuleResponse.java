package com.learningapp.dto.response;

import com.learningapp.dto.StudyModuleDTO;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudyModuleResponse extends StudyModuleDTO {

    private List<FlashcardResponse> flashcards;
    private List<QuizResponse> quizzes;
}
