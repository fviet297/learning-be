package com.learningapp.entity;

import lombok.*;

import javax.persistence.*;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public StudyModuleEntity getStudyModuleEntity() {
        return studyModuleEntity;
    }

    public void setStudyModuleEntity(StudyModuleEntity studyModuleEntity) {
        this.studyModuleEntity = studyModuleEntity;
    }
}