package com.learningapp.entity;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "quizzes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuizEntity extends BaseEntity{

    @Column(name = "question", nullable = false, columnDefinition = "TEXT")
    private String question;

    @Column(name = "options", nullable = false, columnDefinition = "TEXT")
    private String options; // JSON array of 4 options

    @Column(name = "correct_answer", nullable = false)
    private int correctAnswer; // 0-3 index of correct option

    @ManyToOne
    @JoinColumn(name = "study_module_id")
    private StudyModuleEntity studyModuleEntity;
}