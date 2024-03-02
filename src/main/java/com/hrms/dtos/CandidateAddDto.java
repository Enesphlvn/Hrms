package com.hrms.dtos;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateAddDto {
    private String name;
    private String surname;
    private int age;
    private String city;
    private String profession;
    @Email
    private String emailAddress;
    private String password;
}
