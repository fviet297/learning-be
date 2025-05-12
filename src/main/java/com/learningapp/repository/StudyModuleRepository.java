package com.learningapp.repository;

import com.learningapp.entity.StudyModuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyModuleRepository extends JpaRepository<StudyModuleEntity, String> {
} 