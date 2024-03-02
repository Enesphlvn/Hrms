package com.hrms.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployerDetailDto {

    private int id;
    private String emailAddress;
    private String companyName;
//    private String jobAdvertTitle;
//    private boolean jobAdvertSituation;
}
