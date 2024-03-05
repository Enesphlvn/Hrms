package com.hrms.dtos.candidateDtos;

import com.hrms.domain.JobAdvert;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetCandidateDetailDto {

    private int id;
    private String emailAddress;
    private String name;
    private String surname;
    private int age;
    private String cityName;
    private String profession;
    private List<JobAdvert> jobAdverts;
}
