package com.learningapp.controller;

import com.learningapp.entity.StudyModule;
import com.learningapp.repository.StudyModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/study-modules")
public class StudyModuleController {

    private final StudyModuleRepository studyModuleRepository;

    @Autowired
    public StudyModuleController(StudyModuleRepository studyModuleRepository) {
        this.studyModuleRepository = studyModuleRepository;
    }

    @PostMapping
    public ResponseEntity<StudyModule> createStudyModule(@RequestBody StudyModule studyModule) {
        if (studyModule.getName() == null || studyModule.getName().trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        StudyModule savedStudyModule = studyModuleRepository.save(studyModule);
        return ResponseEntity.ok(savedStudyModule);
    }

    @GetMapping
    public ResponseEntity<List<StudyModule>> getAllStudyModules() {
        List<StudyModule> studyModules = studyModuleRepository.findAll();
        return ResponseEntity.ok(studyModules);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudyModule> getStudyModuleById(@PathVariable Long id) {
        StudyModule studyModule = studyModuleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Study module not found with id: " + id));
        return ResponseEntity.ok(studyModule);
    }
} 