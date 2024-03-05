package com.hrms.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hrms.core.entities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "candidates")
public class Candidate extends User {

    @Column(name = "first_name")
    private String name;

    @Column(name = "last_name")
    private String surname;

    @Column(name = "age")
    private int age;

    @Column(name = "profession")
    private String profession;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "city_id")
    private City city;

    @OneToMany(mappedBy = "candidate", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<JobAdvert> job_adverts;
}
