package com.hrms.business.abstracts;

import com.hrms.core.utilities.results.DataResult;
import com.hrms.core.utilities.results.Result;
import com.hrms.dtos.employerDtos.CreateEmployerDto;
import com.hrms.dtos.employerDtos.GetEmployerDto;
import com.hrms.dtos.employerDtos.UpdateEmployerDto;

import java.util.List;

public interface EmployerService {
    DataResult<List<GetEmployerDto>> getAll();
    Result add(CreateEmployerDto createEmployerDto);
    Result delete(int id);
    Result update(int id, UpdateEmployerDto updateEmployerDto);
}
