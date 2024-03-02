package com.hrms.business.abstracts;

import com.hrms.core.utilities.results.DataResult;
import com.hrms.core.utilities.results.Result;
import com.hrms.domain.Candidate;
import com.hrms.dtos.CandidateAddDto;
import com.hrms.dtos.CandidateGetAllDto;
import com.hrms.dtos.CandidateUpdateDto;

import java.util.List;

public interface CandidateService {
    DataResult<List<CandidateGetAllDto>> getAll();
    Result add(CandidateAddDto candidateAddDto);
    Result delete(int id);
    Result update(int id, CandidateUpdateDto candidateUpdateDto);
}
