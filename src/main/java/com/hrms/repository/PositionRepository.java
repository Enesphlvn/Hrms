package com.hrms.repository;

import com.hrms.domain.Position;
import com.hrms.dtos.PositionsGetAllDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PositionRepository extends JpaRepository<Position, Integer> {
    @Query("SELECT new com.hrms.dtos.PositionsGetAllDto(p.positionId, p.positionName) FROM Position p")
    List<PositionsGetAllDto> getPositionsDetails();
    Position getById(int id);
}
