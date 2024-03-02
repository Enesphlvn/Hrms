package com.hrms.controllers;

import com.hrms.business.abstracts.CandidateService;
import com.hrms.domain.Candidate;
import com.hrms.dtos.CandidateAddDto;
import com.hrms.dtos.CandidateUpdateDto;
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

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody CandidateAddDto candidateAddDto){
        return ResponseEntity.ok(this.candidateService.add(candidateAddDto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@Valid @RequestParam int id){
        return ResponseEntity.ok(this.candidateService.delete(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@Valid @PathVariable("id") int id, @Valid @RequestBody CandidateUpdateDto candidateUpdateDto){
        return ResponseEntity.ok(this.candidateService.update(id, candidateUpdateDto));
    }
}
