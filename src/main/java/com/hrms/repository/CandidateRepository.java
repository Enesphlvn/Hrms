package com.hrms.repository;

import com.hrms.domain.Candidate;
import com.hrms.dtos.candidateDtos.GetCandidateDetailDto;
import com.hrms.dtos.candidateDtos.GetCandidateDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidate, Integer> {
    @Query("SELECT new com.hrms.dtos.candidateDtos.GetCandidateDetailDto(c.id, c.emailAddress, c.name, c.surname, c.age, c.profession, " +
            "c.city.cityName, (SELECT new com.hrms.domain.JobAdvert(j.id, j.title, j.description, j.minSalary, j.maxSalary," +
            "j.numberOfAvailablePositions, j.deadline, j.advertSituation, j.employer.companyName, j.candidate.name," +
            "j.candidate.surname, j.position.positionName, j.city.cityName) " +
            "FROM JobAdvert j WHERE j.candidate.id = c.id)) FROM Candidate c")
    List<GetCandidateDetailDto> getCandidateDetailDto();

    @Query("SELECT new com.hrms.dtos.candidateDtos.GetCandidateDto(c.id, c.emailAddress, c.name, c.surname, c.age, c.profession, c.city.cityId)" +
            "FROM Candidate c")
    List<GetCandidateDto> getCandidateDto();
    Candidate getById(int id);
}
