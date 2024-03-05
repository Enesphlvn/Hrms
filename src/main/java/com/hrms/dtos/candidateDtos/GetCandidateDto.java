package com.hrms.dtos.candidateDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCandidateDto {

    private int id;
    private String emailAddress;
    private String name;
    private String surname;
    private int age;
    private String profession;
    private int cityId;
}
