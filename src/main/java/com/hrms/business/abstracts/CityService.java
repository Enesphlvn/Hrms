package com.hrms.business.abstracts;

import com.hrms.core.utilities.results.DataResult;
import com.hrms.core.utilities.results.Result;
import com.hrms.domain.City;
import com.hrms.dtos.CityAddDto;
import com.hrms.dtos.CityGetAllDto;
import com.hrms.dtos.CityUpdateDto;

import java.util.List;

public interface CityService {
    DataResult<List<CityGetAllDto>> getAll();
    Result add(CityAddDto cityAddDto);
    Result delete(int id);
    Result update(int id, CityUpdateDto cityUpdateDto);

}
