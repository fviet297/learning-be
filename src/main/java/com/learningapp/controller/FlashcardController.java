package com.learningapp.controller;

import com.learningapp.dto.ResponseData;
import com.learningapp.dto.request.FlashcardRequest;
import com.learningapp.service.FlashcardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flashcards")
public class FlashcardController {

    private final FlashcardService flashcardService;

    @Autowired
    public FlashcardController(FlashcardService flashcardService) {
        this.flashcardService = flashcardService;
    }

    @PostMapping
    public ResponseEntity<ResponseData> createFlashcard(@Valid @RequestBody FlashcardRequest flashcardRequest) {
        return ResponseEntity.ok(
                ResponseData.builder()
                        .data(flashcardService.create(flashcardRequest))
                        .build());
    }

//    @GetMapping("/random")
//    public ResponseEntity<FlashcardEntity> getRandomFlashcard() {
//        List<FlashcardEntity> learnFlashcardEntities = flashcardRepository.findByStatus("LEARN");
//        if (learnFlashcardEntities.isEmpty()) {
//            return ResponseEntity.noContent().build();
//        }
//        FlashcardEntity randomFlashcardEntity = learnFlashcardEntities.get(new Random().nextInt(learnFlashcardEntities.size()));
//        return ResponseEntity.ok(randomFlashcardEntity);
//    }

//    @PutMapping("/{id}")
//    public ResponseEntity<FlashcardEntity> updateFlashcardStatus(@PathVariable Long id, @RequestBody FlashcardEntity updatedFlashcardEntity) {
//        FlashcardEntity flashcardEntity = flashcardRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Flashcard not found with id: " + id));
//        if (!"LEARN".equals(updatedFlashcardEntity.getStatus()) && !"KNOWN".equals(updatedFlashcardEntity.getStatus())) {
//            return ResponseEntity.badRequest().build();
//        }
//        flashcardEntity.setStatus(updatedFlashcardEntity.getStatus());
//        FlashcardEntity savedFlashcardEntity = flashcardRepository.save(flashcardEntity);
//        return ResponseEntity.ok(savedFlashcardEntity);
//    }
}