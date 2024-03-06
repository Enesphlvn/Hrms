package com.hrms.controllers;

import com.hrms.business.abstracts.JobAdvertService;
import com.hrms.dtos.jobAdvertDtos.CreateJobAdvertDto;
import com.hrms.dtos.jobAdvertDtos.UpdateJobAdvertDto;
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

    @GetMapping("/getJobAdvertDetails")
    public ResponseEntity<?> getJobAdvertDetails(){
        return ResponseEntity.ok(this.jobAdvertService.getJobAdvertDetails());
    }

    @GetMapping("/findByAdvertSituationTrue")
    public ResponseEntity<?> findByAdvertSituationTrue(){
        return ResponseEntity.ok(this.jobAdvertService.findByAdvertSituationTrue());
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody CreateJobAdvertDto createJobAdvertDto){
        return ResponseEntity.ok(this.jobAdvertService.add(createJobAdvertDto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@Valid @RequestParam int id){
        return ResponseEntity.ok(this.jobAdvertService.delete(id));
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@Valid @RequestBody UpdateJobAdvertDto updateJobAdvertDto){
        return ResponseEntity.ok(this.jobAdvertService.update(updateJobAdvertDto));
    }
}
