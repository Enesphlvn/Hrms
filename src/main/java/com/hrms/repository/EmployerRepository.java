package com.hrms.repository;

import com.hrms.domain.Employer;
import com.hrms.dtos.EmployerDetailDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployerRepository extends JpaRepository<Employer, Integer> {
    @Query("SELECT new com.hrms.dtos.EmployerDetailDto(e.id, e.emailAddress, e.companyName)" +
            "FROM Employer e")
    List<EmployerDetailDto> getEmployerDetails();
    Employer getById(int id);
}
