package com.hrms.controllers;

import com.hrms.business.abstracts.JobAdvertService;
import com.hrms.domain.JobAdvert;
import com.hrms.dtos.JobAdvertAddDto;
import com.hrms.dtos.JobAdvertUpdateDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jobAdverts")
public class JobAdvertsController {
    private final JobAdvertService jobAdvertService;

    @Autowired
    public JobAdvertsController(JobAdvertService jobAdvertService) {
        this.jobAdvertService = jobAdvertService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(this.jobAdvertService.getAll());
    }

    @GetMapping("/findByAdvertSituationTrue")
    public ResponseEntity<?> findByAdvertSituationTrue(){
        return ResponseEntity.ok(this.jobAdvertService.findByAdvertSituationTrue());
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody JobAdvertAddDto jobAdvertAddDto){
        return ResponseEntity.ok(this.jobAdvertService.add(jobAdvertAddDto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@Valid @RequestParam int id){
        return ResponseEntity.ok(this.jobAdvertService.delete(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@Valid @PathVariable("id") int id, @Valid @RequestBody JobAdvertUpdateDto jobAdvertUpdateDto){
        return ResponseEntity.ok(this.jobAdvertService.update(id, jobAdvertUpdateDto));
    }
}
