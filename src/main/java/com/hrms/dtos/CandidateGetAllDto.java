package com.hrms.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateGetAllDto {

    private int id;
    private String emailAddress;
    private String name;
    private String surname;
    private int age;
    private String city;
    private String profession;
}
