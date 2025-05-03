package com.learningapp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "quizzes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "question", nullable = false, columnDefinition = "TEXT")
    private String question;

    @Column(name = "options", nullable = false, columnDefinition = "TEXT")
    private String options; // JSON string: ["option1", "option2", "option3", "option4"]

    @Column(name = "correct_answer", nullable = false)
    private int correctAnswer; // Index of correct option (0-3)
}