package com.hrms.repository;

import com.hrms.domain.Candidate;
import com.hrms.dtos.CandidateGetAllDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidate, Integer> {
    @Query("SELECT new com.hrms.dtos.CandidateGetAllDto(c.id, c.emailAddress, c.name, c.surname, c.age, c.city, " +
            "c.profession) FROM Candidate c")
    List<CandidateGetAllDto> getCandidateDetails();
    Candidate getById(int id);
}
