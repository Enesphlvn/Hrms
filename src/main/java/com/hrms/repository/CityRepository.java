package com.hrms.repository;

import com.hrms.domain.City;
import com.hrms.dtos.CityGetAllDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Integer> {
    @Query("SELECT new com.hrms.dtos.CityGetAllDto(c.cityId, c.cityName) FROM City c")
    List<CityGetAllDto> getCityDetails();
    City getById(int id);
}

