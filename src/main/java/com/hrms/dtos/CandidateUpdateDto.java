package com.hrms.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateUpdateDto {

    private String emailAddress;
    private String password;
    private String name;
    private String surname;
    private int age;
    private String city;
    private String profession;
}
