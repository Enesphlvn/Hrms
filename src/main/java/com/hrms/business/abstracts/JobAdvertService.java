package com.hrms.business.abstracts;

import com.hrms.core.utilities.results.DataResult;
import com.hrms.core.utilities.results.Result;
import com.hrms.domain.JobAdvert;
import com.hrms.dtos.JobAdvertAddDto;
import com.hrms.dtos.JobAdvertDetailDto;
import com.hrms.dtos.JobAdvertUpdateDto;

import java.util.List;

public interface JobAdvertService {
    DataResult<List<JobAdvertDetailDto>> getAll();
    Result add(JobAdvertAddDto jobAdvertAddDto);
    Result delete(int id);
    Result update(int id, JobAdvertUpdateDto jobAdvertUpdateDto);
    DataResult<List<JobAdvert>> findByAdvertSituationTrue();
}
