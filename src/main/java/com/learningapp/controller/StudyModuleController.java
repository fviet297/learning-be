//package com.learningapp.controller;
//
//import com.learningapp.entity.StudyModuleEntity;
//import com.learningapp.repository.StudyModuleRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/study-modules")
//public class StudyModuleController {
//
//    private final StudyModuleRepository studyModuleRepository;
//
//    @Autowired
//    public StudyModuleController(StudyModuleRepository studyModuleRepository) {
//        this.studyModuleRepository = studyModuleRepository;
//    }
//
//    @PostMapping
//    public ResponseEntity<StudyModuleEntity> createStudyModule(@RequestBody StudyModuleEntity studyModuleEntity) {
//        if (studyModuleEntity.getName() == null || studyModuleEntity.getName().trim().isEmpty()) {
//            return ResponseEntity.badRequest().build();
//        }
//        StudyModuleEntity savedStudyModuleEntity = studyModuleRepository.save(studyModuleEntity);
//        return ResponseEntity.ok(savedStudyModuleEntity);
//    }
//
//    @GetMapping
//    public ResponseEntity<List<StudyModuleEntity>> getAllStudyModules() {
//        List<StudyModuleEntity> studyModuleEntities = studyModuleRepository.findAll();
//        return ResponseEntity.ok(studyModuleEntities);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<StudyModuleEntity> getStudyModuleById(@PathVariable Long id) {
//        StudyModuleEntity studyModuleEntity = studyModuleRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Study module not found with id: " + id));
//        return ResponseEntity.ok(studyModuleEntity);
//    }
//}