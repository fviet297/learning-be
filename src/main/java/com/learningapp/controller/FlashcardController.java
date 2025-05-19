package com.learningapp.controller;

import com.learningapp.dto.ResponseData;
import com.learningapp.dto.request.FlashcardRequest;
import com.learningapp.dto.response.FlashcardResponse;
import com.learningapp.service.FlashcardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

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
        final FlashcardResponse flashcardResponse = flashcardService.create(flashcardRequest);
        return ResponseEntity.ok(ResponseData.builder().data(flashcardResponse).build());
    }

    @GetMapping("/random/{studyModuleId}")
    public ResponseEntity<ResponseData> getRandomFlashcard(@PathVariable String studyModuleId) {
        final FlashcardResponse flashcardResponse = flashcardService.random(studyModuleId);
        if (Objects.isNull(flashcardResponse)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ResponseData.builder().data(flashcardResponse).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseData> updateFlashcardStatus(@PathVariable String id, @RequestBody FlashcardRequest flashcardRequest) {
        final FlashcardResponse flashcardResponse = flashcardService.updateFlashcardStatus(id,flashcardRequest.getStatus());
        return ResponseEntity.ok(ResponseData.builder().data(flashcardResponse).build());
    }
}