package com.hrms.business.concretes;

import com.hrms.business.abstracts.CityService;
import com.hrms.core.utilities.results.*;
import com.hrms.dtos.CityAddDto;
import com.hrms.dtos.CityGetAllDto;
import com.hrms.dtos.CityUpdateDto;
import com.hrms.repository.CityRepository;
import com.hrms.domain.City;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityImpl implements CityService {
    private final CityRepository cityRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CityImpl(CityRepository cityRepository, ModelMapper modelMapper) {
        this.cityRepository = cityRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DataResult<List<CityGetAllDto>> getAll() {
        return new SuccessDataResult<List<CityGetAllDto>>(this.cityRepository.getCityDetails(), "Şehirler listelendi");
    }

    @Override
    public Result add(CityAddDto cityAddDto) {
        City city = modelMapper.map(cityAddDto, City.class);
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
    public Result update(int id, CityUpdateDto cityUpdateDto) {
        Optional<City> resultCity = this.cityRepository.findById(id);
        if (resultCity.isPresent()) {
            resultCity.get().setCityName(cityUpdateDto.getCityName());

            modelMapper.map(this.cityRepository.save(resultCity.get()), CityUpdateDto.class);
            return new SuccessResult("Şehir güncellendi");
        } else {
            return new ErrorResult("Şehir bulunamadı");
        }
    }
}
