package com.hrms.repository;

import com.hrms.domain.JobAdvert;
import com.hrms.dtos.JobAdvertDetailDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobAdvertRepository extends JpaRepository<JobAdvert, Integer> {
    @Query("SELECT new com.hrms.dtos.JobAdvertDetailDto(j.id, j.title, j.description, j.minSalary, j.maxSalary, " +
            "j.numberOfAvailablePositions, j.deadline, j.advertSituation, e.companyName, c.name, c.surname, p.positionName, ci.cityName) " +
            "FROM JobAdvert j INNER JOIN j.employer e INNER JOIN j.candidate c INNER JOIN j.position p INNER JOIN j.city ci")
    List<JobAdvertDetailDto> getJobAdvertDetails();
    List<JobAdvert> getByAdvertSituationTrue();
    JobAdvert getById(int id);
}
