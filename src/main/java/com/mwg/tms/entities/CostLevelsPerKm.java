package com.mwg.tms.entities;

import jakarta.persistence.*;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shippingservicepriceid")
    private ShippingServicePrice shippingservicepriceid;

}