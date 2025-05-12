package com.learningapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "flashcards")
public class FlashcardEntity extends BaseEntity {

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "status", nullable = false, length = 10)
    private String status; // "LEARN" or "KNOWN"

    @ManyToOne
    @JoinColumn(name = "study_module_id", nullable = false)
    private StudyModuleEntity studyModuleEntity;
}