package com.hrms.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "job_adverts")
public class JobAdvert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_advert_id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "min_salary")
    private int minSalary;

    @Column(name = "max_salary")
    private int maxSalary;

    @Column(name = "number_of_available_positions")
    private int numberOfAvailablePositions;

    @Column(name = "deadline")
    private LocalDate deadline;

    @Column(name = "advert_situation")
    private boolean advertSituation;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "employer_")
    private Employer employer;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "position_id")
    private Position position;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "city_id")
    private City city;
}
