package com.hrms.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hrms.core.entities.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank
    @NotNull
    @Column(name = "company_name")
    private String companyName;

    @OneToMany(mappedBy = "employer", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<JobAdvert> job_adverts;
}
