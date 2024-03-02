package com.hrms.business.abstracts;

import com.hrms.core.utilities.results.DataResult;
import com.hrms.core.utilities.results.Result;
import com.hrms.domain.Position;
import com.hrms.dtos.PositionAddDto;
import com.hrms.dtos.PositionUpdateDto;
import com.hrms.dtos.PositionsGetAllDto;

import java.util.List;

public interface PositionService {
    DataResult<List<PositionsGetAllDto>> getAll();
    Result add(PositionAddDto positionAddDto);
    Result delete(int id);
    Result update(int id, PositionUpdateDto positionUpdateDto);
}
