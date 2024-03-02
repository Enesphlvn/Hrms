package com.hrms.dtos;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployerAddDto {

    @Email
    private String emailAddress;
    private String password;
    private String companyName;
}
