package com.learningapp.repository;

import com.learningapp.entity.QuizResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizResultRepository extends JpaRepository<QuizResultEntity, String> {
}