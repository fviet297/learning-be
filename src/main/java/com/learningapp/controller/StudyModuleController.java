package com.learningapp.controller;

import com.learningapp.dto.ResponseData;
import com.learningapp.dto.request.StudyModuleRequest;
import com.learningapp.dto.response.StudyModuleResponse;
import com.learningapp.service.StudyModuleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/study-modules")
public class StudyModuleController {

    private final StudyModuleService studyModuleService;

    @Autowired
    public StudyModuleController(StudyModuleService studyModuleService) {
        this.studyModuleService = studyModuleService;
    }

    @PostMapping
    public ResponseEntity<ResponseData> createStudyModule(@Valid @RequestBody StudyModuleRequest studyModuleRequest) {
        final StudyModuleResponse studyModuleResponse = studyModuleService.create(studyModuleRequest);
        return ResponseEntity.ok(ResponseData.builder().data(studyModuleResponse).build());
    }

    //    @GetMapping
//    public ResponseEntity<List<StudyModuleEntity>> getAllStudyModules() {
//        List<StudyModuleEntity> studyModuleEntities = studyModuleRepository.findAll();
//        return ResponseEntity.ok(studyModuleEntities);
//    }
//
    @GetMapping("/{id}")
    public ResponseEntity<ResponseData> getStudyModuleById(@PathVariable String id) {
        final StudyModuleResponse studyModuleResponse = studyModuleService.getById(id);
        return ResponseEntity.ok(ResponseData.builder().data(studyModuleResponse).build());
    }
}