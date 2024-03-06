package com.hrms.repository;

import com.hrms.domain.JobAdvert;
import com.hrms.dtos.jobAdvertDtos.GetJobAdvertDetailDto;
import com.hrms.dtos.jobAdvertDtos.GetJobAdvertDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobAdvertRepository extends JpaRepository<JobAdvert, Integer> {

    @Query("SELECT new com.hrms.dtos.jobAdvertDtos.GetJobAdvertDto(j.id, j.title, j.description, j.minSalary, j.maxSalary, " +
            "j.numberOfAvailablePositions, j.deadline, j.advertSituation, j.employer.id, j.position.positionId, j.candidate.id, " +
            "j.city.cityId) FROM JobAdvert j ORDER BY j.id")
    List<GetJobAdvertDto> getJobAdvertDto();
    @Query("SELECT new com.hrms.dtos.jobAdvertDtos.GetJobAdvertDetailDto(j.id, j.title, j.description, j.minSalary, j.maxSalary, " +
            "j.numberOfAvailablePositions, j.deadline, j.advertSituation, j.employer.companyName, j.candidate.name, j.candidate.surname," +
            "j.position.positionName, j.city.cityName) FROM JobAdvert j ORDER BY j.id")
    List<GetJobAdvertDetailDto> getJobAdvertDetails();
    List<JobAdvert> getByAdvertSituationTrue();
}
