package com.learningapp.entity;

import javax.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "study_modules")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudyModule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "studyModule", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Flashcard> flashcards;

    @OneToMany(mappedBy = "studyModule", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Quiz> quizzes;
} 