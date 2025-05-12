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
public class StudyModuleEntity extends BaseEntity{

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "studyModule", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<FlashcardEntity> flashcardEntities;

    @OneToMany(mappedBy = "studyModule", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<QuizEntity> quizEntities;
} 