package com.learningapp.entity;

import com.learningapp.enums.FlashcardStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "flashcards")
@Getter
@Setter
public class Flashcard extends BaseEntity {

    @Column(name = "question", nullable = false, columnDefinition = "TEXT")
    private String question;

    @Column(name = "answer", nullable = false, columnDefinition = "TEXT")
    private String answer;

    @Enumerated(EnumType.STRING)
    private FlashcardStatus status;

    @ManyToOne
    @JoinColumn(name = "study_module_id", nullable = false)
    private StudyModule studyModule;
}