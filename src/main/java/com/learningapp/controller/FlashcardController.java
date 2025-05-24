package com.learningapp.controller;

import com.learningapp.dto.ResponseData;
import com.learningapp.dto.request.FlashcardRequest;
import com.learningapp.dto.request.FlashcardRequestBulk;
import com.learningapp.dto.response.FlashcardResponse;
import com.learningapp.service.FlashcardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public ResponseEntity<ResponseData> createFlashcardsBulk(@Valid @RequestBody FlashcardRequestBulk flashcardRequestBulk) {
        final List<FlashcardResponse> flashcardResponse = flashcardService.createBulk(flashcardRequestBulk);
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

    @GetMapping("/{studyModuleId}")
    public ResponseEntity<ResponseData> getFlashcardsByModuleId(@PathVariable String studyModuleId) {
        final List<FlashcardResponse> flashcardResponse = flashcardService.getFlashcardsByModuleId(studyModuleId);
        if (Objects.isNull(flashcardResponse)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ResponseData.builder().data(flashcardResponse).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData> deleteFlashcard(@PathVariable String id) {
        return ResponseEntity.ok(ResponseData.builder().data(flashcardService.deleteFlashcard(id)).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseData> updateFlashcardStatus(@PathVariable String id, @RequestBody FlashcardRequest flashcardRequest) {
        final FlashcardResponse flashcardResponse = flashcardService.updateFlashcardStatus(id,flashcardRequest.getStatus());
        return ResponseEntity.ok(ResponseData.builder().data(flashcardResponse).build());
    }
}