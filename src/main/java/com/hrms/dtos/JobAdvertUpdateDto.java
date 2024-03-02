package com.hrms.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobAdvertUpdateDto {

    private String title;
    private String description;
    private int minSalary;
    private int maxSalary;
    private int numberOfAvailablePositions;
    private LocalDate deadline;
    private boolean advertSituation;
}
