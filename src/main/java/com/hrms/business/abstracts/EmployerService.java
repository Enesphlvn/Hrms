package com.hrms.business.abstracts;

import com.hrms.core.utilities.results.DataResult;
import com.hrms.core.utilities.results.Result;
import com.hrms.domain.Employer;
import com.hrms.dtos.EmployerAddDto;
import com.hrms.dtos.EmployerDetailDto;
import com.hrms.dtos.EmployerUpdateDto;

import java.util.List;

public interface EmployerService {
    DataResult<List<EmployerDetailDto>> getAll();
    Result add(EmployerAddDto employerAddDto);
    Result delete(int id);
    Result update(int id, EmployerUpdateDto employerUpdateDto);
}
