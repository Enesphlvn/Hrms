package com.hrms.controllers;

import com.hrms.business.abstracts.EmployerService;
import com.hrms.dtos.employerDtos.CreateEmployerDto;
import com.hrms.dtos.employerDtos.UpdateEmployerDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employers")
public class EmployersController {
    private final EmployerService employerService;

    @Autowired
    public EmployersController(EmployerService employerService) {
        this.employerService = employerService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(this.employerService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody CreateEmployerDto createEmployerDto){
        return ResponseEntity.ok(this.employerService.add(createEmployerDto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@Valid @RequestParam int id){
        return ResponseEntity.ok(this.employerService.delete(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@Valid @PathVariable("id") int id, @Valid @RequestBody UpdateEmployerDto updateEmployerDto){
        return ResponseEntity.ok(this.employerService.update(id, updateEmployerDto));
    }
}
