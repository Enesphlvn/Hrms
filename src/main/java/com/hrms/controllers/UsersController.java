package com.hrms.controllers;

import com.hrms.business.abstracts.UserService;
import com.hrms.core.entities.User;
import com.hrms.dtos.UserAddDto;
import com.hrms.dtos.UserUpdateDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(this.userService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody UserAddDto userAddDto){
        return ResponseEntity.ok(this.userService.add(userAddDto));
    }
    
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@Valid @RequestParam int id){
        return ResponseEntity.ok(this.userService.delete(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@Valid @PathVariable("id") int id, @Valid @RequestBody UserUpdateDto userUpdateDto){
        return ResponseEntity.ok(this.userService.update(id, userUpdateDto));
    }
}
