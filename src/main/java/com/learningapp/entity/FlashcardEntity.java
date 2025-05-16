package com.learningapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "flashcards")
@Getter
@Setter
public class FlashcardEntity extends BaseEntity {

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "status", nullable = false, length = 10)
    private String status; // "LEARN" or "KNOWN"

    @ManyToOne
    @JoinColumn(name = "study_module_id", nullable = false)
    private StudyModuleEntity studyModuleEntity;
}