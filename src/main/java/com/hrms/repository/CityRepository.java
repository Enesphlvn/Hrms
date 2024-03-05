package com.hrms.repository;

import com.hrms.domain.City;
import com.hrms.dtos.cityDtos.GetCityDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Integer> {
    @Query("SELECT new com.hrms.dtos.cityDtos.GetCityDto(c.cityId, c.cityName) FROM City c")
    List<GetCityDto> getCityDto();
    City getById(int id);
}

