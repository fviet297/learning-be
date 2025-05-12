package com.learningapp.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "quiz_results")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuizResultEntity extends BaseEntity{

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "quiz_id", nullable = false)
    private Long quizId;

    @Column(name = "score", nullable = false)
    private int score;
}