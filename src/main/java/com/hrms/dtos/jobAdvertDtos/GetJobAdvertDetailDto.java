package com.hrms.dtos.jobAdvertDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetJobAdvertDetailDto {

    private int id;
    private String title;
    private String description;
    private int minSalary;
    private int maxSalary;
    private int numberOfAvailablePositions;
    private LocalDate deadline;
    private boolean advertSituation;
    private String companyName;
    private String candidateName;
    private String candidateSurname;
    private String positionName;
    private String cityName;
}
