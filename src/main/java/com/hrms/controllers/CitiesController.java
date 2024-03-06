package com.hrms.controllers;

import com.hrms.business.abstracts.CityService;
import com.hrms.dtos.cityDtos.CreateCityDto;
import com.hrms.dtos.cityDtos.UpdateCityDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cities")
public class CitiesController {
    private final CityService cityService;
    @Autowired
    public CitiesController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(this.cityService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody CreateCityDto cityAddDto){
        return ResponseEntity.ok(this.cityService.add(cityAddDto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@Valid @RequestParam int id){
        return ResponseEntity.ok(this.cityService.delete(id));
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@Valid @RequestBody UpdateCityDto updateCityDto){
        return ResponseEntity.ok(this.cityService.update(updateCityDto));
    }
}
