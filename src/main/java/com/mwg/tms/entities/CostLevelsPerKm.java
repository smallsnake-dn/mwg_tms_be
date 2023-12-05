package com.mwg.tms.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cost_levels_per_km")
public class CostLevelsPerKm {
    @Id
    @Column(name = "costlevelperkm", nullable = false)
    private Integer id;

    @Column(name = "ordinalnumber")
    private Integer ordinalnumber;

    @Column(name = "distancelevel")
    private Double distancelevel;

    @Column(name = "costs")
    private Double costs;

}