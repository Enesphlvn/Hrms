package com.hrms.controllers;

import com.hrms.business.abstracts.PositionService;
import com.hrms.dtos.PositionAddDto;
import com.hrms.dtos.PositionUpdateDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/positions")
public class PositionsController {
    private final PositionService positionService;

    @Autowired
    public PositionsController(PositionService positionService) {
        this.positionService = positionService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(this.positionService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody PositionAddDto positionAddDto){
        return ResponseEntity.ok(this.positionService.add(positionAddDto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@Valid @RequestParam int id){
        return ResponseEntity.ok(this.positionService.delete(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@Valid @PathVariable("id") int id, @Valid @RequestBody PositionUpdateDto positionUpdateDto){
        return ResponseEntity.ok(this.positionService.update(id, positionUpdateDto));
    }
}
