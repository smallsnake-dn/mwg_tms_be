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
@Table(name = "vehicle")
public class Vehicle {
    @Id
    @Column(name = "vehilceid", nullable = false, length = 36)
    private String id;

    @Column(name = "typeofvehicleid")
    private Integer typeofvehicleid;

    @Column(name = "licenseplate", length = 10)
    private String licenseplate;

    @Column(name = "shippingpartnerid")
    private Integer shippingpartnerid;

}