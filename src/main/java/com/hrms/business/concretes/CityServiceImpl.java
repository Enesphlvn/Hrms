package com.hrms.business.concretes;

import com.hrms.business.abstracts.CityService;
import com.hrms.core.utilities.results.*;
import com.hrms.dtos.cityDtos.CreateCityDto;
import com.hrms.dtos.cityDtos.GetCityDto;
import com.hrms.dtos.cityDtos.UpdateCityDto;
import com.hrms.repository.CityRepository;
import com.hrms.domain.City;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository, ModelMapper modelMapper) {
        this.cityRepository = cityRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DataResult<List<GetCityDto>> getAll() {
        return new SuccessDataResult<List<GetCityDto>>(this.cityRepository.getCityDto(), "Şehirler listelendi");
    }

    @Override
    public Result add(CreateCityDto createCityDto) {
        City city = this.modelMapper.map(createCityDto, City.class);
        String cityName = city.getCityName().toLowerCase();
        List<City> existingCities = this.cityRepository.findAll();

        for (City existingCity : existingCities) {
            if (existingCity.getCityName().equalsIgnoreCase(cityName)) {
                return new ErrorResult("Bu şehir zaten mevcut");
            }
        }

        this.cityRepository.save(city);
        return new SuccessResult("Şehir eklendi");
    }

    @Override
    public Result delete(int id) {
        City city = this.cityRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Şehir bulunamadı"));
        this.cityRepository.delete(city);
        return new SuccessResult(city.getCityName() + " adlı şehir silindi");
    }

    @Override
    public Result update(int id, UpdateCityDto updateCityDto) {
        Optional<City> resultCity = this.cityRepository.findById(id);
        if (resultCity.isPresent()) {
            resultCity.get().setCityName(updateCityDto.getCityName());

            this.modelMapper.map(this.cityRepository.save(resultCity.get()), UpdateCityDto.class);
            return new SuccessResult("Şehir güncellendi");
        } else {
            return new ErrorResult("Şehir bulunamadı");
        }
    }
}
