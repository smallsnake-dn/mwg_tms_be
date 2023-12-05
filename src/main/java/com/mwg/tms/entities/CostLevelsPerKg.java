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
@Table(name = "cost_levels_per_kg")
public class CostLevelsPerKg {
    @Id
    @Column(name = "costlevelsperkgid", nullable = false)
    private Integer id;

    @Column(name = "ordinalnumber")
    private Integer ordinalnumber;

    @Column(name = "weightlevel")
    private Double weightlevel;

    @Column(name = "unit")
    private Double unit;

    @Column(name = "costs")
    private Double costs;

}