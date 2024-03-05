package com.hrms.dtos.employerDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetEmployerDto {

    private int id;
    private String emailAddress;
    private String companyName;
}
