package com.hrms.controllers;

import com.hrms.business.abstracts.PositionService;
import com.hrms.dtos.positionDtos.CreatePositionDto;
import com.hrms.dtos.positionDtos.UpdatePositionDto;
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
    public ResponseEntity<?> add(@Valid @RequestBody CreatePositionDto createPositionDto){
        return ResponseEntity.ok(this.positionService.add(createPositionDto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@Valid @RequestParam int id){
        return ResponseEntity.ok(this.positionService.delete(id));
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@Valid @RequestBody UpdatePositionDto updatePositionDto){
        return ResponseEntity.ok(this.positionService.update(updatePositionDto));
    }
}
