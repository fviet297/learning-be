package com.learningapp.repository;

import com.learningapp.entity.StudyModuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudyModuleRepository extends JpaRepository<StudyModuleEntity, String> {
    Optional<StudyModuleEntity> findByIdAndIsDeleteFalse(String id);
} 