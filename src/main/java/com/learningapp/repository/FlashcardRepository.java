package com.learningapp.repository;

import com.learningapp.entity.FlashcardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlashcardRepository extends JpaRepository<FlashcardEntity, String> {
    List<FlashcardEntity> findByStatus(String status);
}