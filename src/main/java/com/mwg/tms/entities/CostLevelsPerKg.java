package com.mwg.tms.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cost_levels_per_kg")
public class CostLevelsPerKg {
    @Id
    @Column(name = "costlevelsperkgid", nullable = false, length = 36)
    private String id;

    @Column(name = "ordinalnumber")
    private Integer ordinalnumber;

    @Column(name = "weightlevel")
    private Double weightlevel;

    @Column(name = "unit")
    private Double unit;

    @Column(name = "costs")
    private Double costs;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shippingservicepriceid")
    private ShippingServicePrice shippingservicepriceid;

}