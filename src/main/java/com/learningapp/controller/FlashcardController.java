package com.learningapp.controller;

import com.learningapp.entity.FlashcardEntity;
import com.learningapp.repository.FlashcardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/flashcards")
public class FlashcardController {

    private final FlashcardRepository flashcardRepository;

    @Autowired
    public FlashcardController(FlashcardRepository flashcardRepository) {
        this.flashcardRepository = flashcardRepository;
    }

    @PostMapping
    public ResponseEntity<FlashcardEntity> createFlashcard(@RequestBody FlashcardEntity flashcardEntity) {
        if (flashcardEntity.getContent() == null || flashcardEntity.getContent().trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        flashcardEntity.setStatus("LEARN");
        FlashcardEntity savedFlashcardEntity = flashcardRepository.save(flashcardEntity);
        return ResponseEntity.ok(savedFlashcardEntity);
    }

    @GetMapping("/random")
    public ResponseEntity<FlashcardEntity> getRandomFlashcard() {
        List<FlashcardEntity> learnFlashcardEntities = flashcardRepository.findByStatus("LEARN");
        if (learnFlashcardEntities.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        FlashcardEntity randomFlashcardEntity = learnFlashcardEntities.get(new Random().nextInt(learnFlashcardEntities.size()));
        return ResponseEntity.ok(randomFlashcardEntity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FlashcardEntity> updateFlashcardStatus(@PathVariable Long id, @RequestBody FlashcardEntity updatedFlashcardEntity) {
        FlashcardEntity flashcardEntity = flashcardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flashcard not found with id: " + id));
        if (!"LEARN".equals(updatedFlashcardEntity.getStatus()) && !"KNOWN".equals(updatedFlashcardEntity.getStatus())) {
            return ResponseEntity.badRequest().build();
        }
        flashcardEntity.setStatus(updatedFlashcardEntity.getStatus());
        FlashcardEntity savedFlashcardEntity = flashcardRepository.save(flashcardEntity);
        return ResponseEntity.ok(savedFlashcardEntity);
    }
}