package com.hrms.repository;

import com.hrms.domain.Employer;
import com.hrms.dtos.employerDtos.GetEmployerDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployerRepository extends JpaRepository<Employer, Integer> {
    @Query("SELECT new com.hrms.dtos.employerDtos.GetEmployerDto(e.id, e.emailAddress, e.companyName)" +
            "FROM Employer e")
    List<GetEmployerDto> getEmployerDto();
    Employer getById(int id);
}
