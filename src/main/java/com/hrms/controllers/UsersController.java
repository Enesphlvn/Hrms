package com.hrms.controllers;

import com.hrms.business.abstracts.UserService;
import com.hrms.dtos.userDtos.CreateUserDto;
import com.hrms.dtos.userDtos.UpdateUserDto;
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
    public ResponseEntity<?> add(@Valid @RequestBody CreateUserDto createUserDto){
        return ResponseEntity.ok(this.userService.add(createUserDto));
    }
    
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@Valid @RequestParam int id){
        return ResponseEntity.ok(this.userService.delete(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@Valid @PathVariable("id") int id, @Valid @RequestBody UpdateUserDto updateUserDto){
        return ResponseEntity.ok(this.userService.update(id, updateUserDto));
    }
}
