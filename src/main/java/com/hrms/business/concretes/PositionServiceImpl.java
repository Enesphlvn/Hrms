package com.hrms.business.concretes;

import com.hrms.business.abstracts.PositionService;
import com.hrms.core.utilities.results.*;
import com.hrms.dtos.positionDtos.CreatePositionDto;
import com.hrms.dtos.positionDtos.UpdatePositionDto;
import com.hrms.dtos.positionDtos.GetPositionDto;
import com.hrms.repository.PositionRepository;
import com.hrms.domain.Position;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PositionServiceImpl implements PositionService {
    private final PositionRepository positionRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PositionServiceImpl(PositionRepository positionRepository, ModelMapper modelMapper) {
        this.positionRepository = positionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DataResult<List<GetPositionDto>> getAll() {
        List<GetPositionDto> positionsDto = this.positionRepository.getPositionDto();
        return new SuccessDataResult<List<GetPositionDto>>(positionsDto, "Pozisyonlar listelendi");
    }

    @Override
    public Result add(CreatePositionDto createPositionDto) {
        Position position = this.modelMapper.map(createPositionDto, Position.class);
        String positionName = position.getPositionName().toLowerCase();
        List<Position> existingPositions = this.positionRepository.findAll();

        for (Position existingPosition : existingPositions) {
            if (existingPosition.getPositionName().equalsIgnoreCase(positionName)) {
                return new ErrorResult("Bu pozisyon zaten mevcut");
            }
        }

        this.positionRepository.save(position);
        return new SuccessResult("Pozisyon eklendi");
    }

    @Override
    public Result delete(int id) {
        Position position = this.positionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Pozisyon bulunamadı"));
        this.positionRepository.delete(position);
        return new SuccessResult(position.getPositionName() + " adlı pozisyon silindi");
    }

    @Override
    public Result update(UpdatePositionDto updatePositionDto) {
        Optional<Position> resultPosition = this.positionRepository.findById(updatePositionDto.getPositionId());
        if (resultPosition.isPresent()) {
            resultPosition.get().setPositionName(updatePositionDto.getPositionName());

            this.modelMapper.map(this.positionRepository.save(resultPosition.get()), UpdatePositionDto.class);
            return new SuccessResult("Pozisyon güncellendi");
        } else {
            return new ErrorResult("Pozisyon bulunamadı");
        }
    }
}
