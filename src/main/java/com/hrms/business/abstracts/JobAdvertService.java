package com.hrms.business.abstracts;

import com.hrms.core.utilities.results.DataResult;
import com.hrms.core.utilities.results.Result;
import com.hrms.domain.JobAdvert;
import com.hrms.dtos.jobAdvertDtos.CreateJobAdvertDto;
import com.hrms.dtos.jobAdvertDtos.GetJobAdvertDetailDto;
import com.hrms.dtos.jobAdvertDtos.UpdateJobAdvertDto;
import com.hrms.dtos.jobAdvertDtos.GetJobAdvertDto;

import java.util.List;

public interface JobAdvertService {
    DataResult<List<GetJobAdvertDto>> getAll();
    DataResult<List<GetJobAdvertDetailDto>> getJobAdvertDetails();
    Result add(CreateJobAdvertDto createJobAdvertDto);
    Result delete(int id);
    Result update(UpdateJobAdvertDto updateJobAdvertDto);
    DataResult<List<JobAdvert>> findByAdvertSituationTrue();
}
