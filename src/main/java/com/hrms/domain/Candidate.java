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
@Table(name = "candidates")
public class Candidate extends User {

    @NotBlank
    @NotNull
    @Column(name = "first_name")
    private String name;

    @NotBlank
    @NotNull
    @Column(name = "last_name")
    private String surname;

    @NotBlank
    @NotNull
    @Column(name = "age")
    private int age;

    @NotBlank
    @NotNull
    @Column(name = "city")
    private String city;

    @NotBlank
    @NotNull
    @Column(name = "profession")
    private String profession;

    @OneToMany(mappedBy = "candidate", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<JobAdvert> job_adverts;
}
