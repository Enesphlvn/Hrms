package com.hrms.business.abstracts;

import com.hrms.core.utilities.results.DataResult;
import com.hrms.core.utilities.results.Result;
import com.hrms.dtos.candidateDtos.CreateCandidateDto;
import com.hrms.dtos.candidateDtos.GetCandidateDetailDto;
import com.hrms.dtos.candidateDtos.GetCandidateDto;
import com.hrms.dtos.candidateDtos.UpdateCandidateDto;

import java.util.List;

public interface CandidateService {
    DataResult<List<GetCandidateDetailDto>> getCandidateDetailDto();
    DataResult<List<GetCandidateDto>> getAll();
    Result add(CreateCandidateDto createCandidateDto);
    Result delete(int id);
    Result update(int id, UpdateCandidateDto updateCandidateDto);
}
