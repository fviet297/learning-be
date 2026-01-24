package com.learningapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Where;
import org.hibernate.type.SqlTypes;

import java.util.List;

@Entity
@Table(name = "quizzes")
@Getter
@Setter
@Where(clause = "is_delete = false")
public class Quiz extends BaseEntity{

    @Column(name = "question", nullable = false, columnDefinition = "TEXT")
    private String question;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "options", nullable = false)
    private List<String> options; // JSON array of 4 options

    @Column(name = "correct_answer", nullable = false)
    private int correctAnswer; // 0-3 index of correct option

    @ManyToOne
    @JoinColumn(name = "study_module_id")
    private StudyModule studyModule;
}
