package com.hrms.business.concretes;

import com.hrms.business.abstracts.PositionService;
import com.hrms.core.utilities.results.*;
import com.hrms.dtos.PositionAddDto;
import com.hrms.dtos.PositionUpdateDto;
import com.hrms.dtos.PositionsGetAllDto;
import com.hrms.repository.PositionRepository;
import com.hrms.domain.Position;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PositionImpl implements PositionService {
    private final PositionRepository positionRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PositionImpl(PositionRepository positionRepository, ModelMapper modelMapper) {
        this.positionRepository = positionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DataResult<List<PositionsGetAllDto>> getAll() {
        List<PositionsGetAllDto> positionsDto = this.positionRepository.getPositionsDetails();
        return new SuccessDataResult<List<PositionsGetAllDto>>(positionsDto, "Pozisyonlar listelendi");
    }

    @Override
    public Result add(PositionAddDto positionAddDto) {
        Position position = modelMapper.map(positionAddDto, Position.class);
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
    public Result update(int id, PositionUpdateDto positionUpdateDto) {
        Optional<Position> resultPosition = this.positionRepository.findById(id);
        if (resultPosition.isPresent()) {
            resultPosition.get().setPositionName(positionUpdateDto.getPositionName());

            modelMapper.map(positionRepository.save(resultPosition.get()), PositionUpdateDto.class);
            return new SuccessResult("Pozisyon güncellendi");
        } else {
            return new ErrorResult("Pozisyon bulunamadı");
        }
    }
}
