package com.hrms.controllers;

import com.hrms.business.abstracts.CandidateService;
import com.hrms.dtos.candidateDtos.CreateCandidateDto;
import com.hrms.dtos.candidateDtos.UpdateCandidateDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/candidates")
public class CandidatesController {
    private final CandidateService candidateService;

    @Autowired
    public CandidatesController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(this.candidateService.getAll());
    }

    @GetMapping("/getCandidateDetails")
    public ResponseEntity<?> getCandidateDetails(){
        return ResponseEntity.ok(this.candidateService.getCandidateDetailDto());
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody CreateCandidateDto candidateAddDto){
        return ResponseEntity.ok(this.candidateService.add(candidateAddDto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@Valid @RequestParam int id){
        return ResponseEntity.ok(this.candidateService.delete(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@Valid @PathVariable("id") int id, @Valid @RequestBody UpdateCandidateDto updateCandidateDto){
        return ResponseEntity.ok(this.candidateService.update(id, updateCandidateDto));
    }
}
