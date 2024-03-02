package com.hrms.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "positions")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "position_id")
    private int positionId;

    @NotBlank
    @NotNull
    @Column(name = "position_name")
    private String positionName;

    @OneToMany(mappedBy = "position", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<JobAdvert> job_adverts;
}
