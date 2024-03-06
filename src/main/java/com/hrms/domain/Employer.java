package com.hrms.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hrms.core.entities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employers")
public class Employer extends User {

    @Column(name = "company_name")
    private String companyName;

    @OneToMany(mappedBy = "employer", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<JobAdvert> job_adverts;
}
