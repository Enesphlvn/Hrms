package com.hrms.business.abstracts;

import com.hrms.core.utilities.results.DataResult;
import com.hrms.core.utilities.results.Result;
import com.hrms.dtos.positionDtos.CreatePositionDto;
import com.hrms.dtos.positionDtos.UpdatePositionDto;
import com.hrms.dtos.positionDtos.GetPositionDto;

import java.util.List;

public interface PositionService {
    DataResult<List<GetPositionDto>> getAll();
    Result add(CreatePositionDto createPositionDto);
    Result delete(int id);
    Result update(UpdatePositionDto updatePositionDto);
}
