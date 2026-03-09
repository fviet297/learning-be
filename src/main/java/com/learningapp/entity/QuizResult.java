package com.learningapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "quiz_results")
@Getter
@Setter
public class QuizResult extends BaseEntity{

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "study_module_id", nullable = false)
    private String studyModuleId;

    @Column(name = "score", nullable = false)
    private int score;
}