package com.learningapp.repository;

import com.learningapp.entity.Flashcard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlashcardRepository extends JpaRepository<Flashcard, String> {
    List<Flashcard> findByStatus(String status);
}