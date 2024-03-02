package com.hrms.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank
    @NotNull
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "min_salary")
    @NotBlank
    @NotNull
    private int minSalary;

    @Column(name = "max_salary")
    @NotBlank
    @NotNull
    private int maxSalary;

    @Column(name = "number_of_available_positions")
    @Min(value = 1, message = "Mevcut pozisyon sayısı 1 veya daha fazla olmalıdır!")
    @NotBlank
    @NotNull
    private int numberOfAvailablePositions;

    @Column(name = "deadline")
    @NotBlank
    @NotNull
    private LocalDate deadline;

    @Column(name = "advert_situation")
    @NotBlank
    @NotNull
    private boolean advertSituation = true;

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
