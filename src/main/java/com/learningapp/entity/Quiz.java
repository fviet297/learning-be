package com.learningapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "quizzes")
@Getter
@Setter
public class Quiz extends BaseEntity{

    @Column(name = "question", nullable = false, columnDefinition = "TEXT")
    private String question;

    @Column(name = "options", nullable = false, columnDefinition = "TEXT")
    private String options; // JSON array of 4 options

    @Column(name = "correct_answer", nullable = false)
    private int correctAnswer; // 0-3 index of correct option

    @ManyToOne
    @JoinColumn(name = "study_module_id")
    private StudyModule studyModule;
}