package com.learningapp.controller;

import com.learningapp.entity.Flashcard;
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
    public ResponseEntity<Flashcard> createFlashcard(@RequestBody Flashcard flashcard) {
        if (flashcard.getContent() == null || flashcard.getContent().trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        flashcard.setStatus("LEARN");
        Flashcard savedFlashcard = flashcardRepository.save(flashcard);
        return ResponseEntity.ok(savedFlashcard);
    }

    @GetMapping("/random")
    public ResponseEntity<Flashcard> getRandomFlashcard() {
        List<Flashcard> learnFlashcards = flashcardRepository.findByStatus("LEARN");
        if (learnFlashcards.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        Flashcard randomFlashcard = learnFlashcards.get(new Random().nextInt(learnFlashcards.size()));
        return ResponseEntity.ok(randomFlashcard);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Flashcard> updateFlashcardStatus(@PathVariable Long id, @RequestBody Flashcard updatedFlashcard) {
        Flashcard flashcard = flashcardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flashcard not found with id: " + id));
        if (!"LEARN".equals(updatedFlashcard.getStatus()) && !"KNOWN".equals(updatedFlashcard.getStatus())) {
            return ResponseEntity.badRequest().build();
        }
        flashcard.setStatus(updatedFlashcard.getStatus());
        Flashcard savedFlashcard = flashcardRepository.save(flashcard);
        return ResponseEntity.ok(savedFlashcard);
    }
}