package com.hrms.business.abstracts;

import com.hrms.core.utilities.results.DataResult;
import com.hrms.core.utilities.results.Result;
import com.hrms.dtos.cityDtos.CreateCityDto;
import com.hrms.dtos.cityDtos.GetCityDto;
import com.hrms.dtos.cityDtos.UpdateCityDto;

import java.util.List;

public interface CityService {
    DataResult<List<GetCityDto>> getAll();
    Result add(CreateCityDto createCityDto);
    Result delete(int id);
    Result update(int id, UpdateCityDto updateCityDto);

}
