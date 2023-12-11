package com.mwg.tms.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "vehicle")
public class Vehicle {
    @Id
    @Column(name = "vehilceid", nullable = false, length = 40)
    private String vehilceid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "typeofvehicleid")
    private TypeOfVehicle typeofvehicleid;

    @Column(name = "licenseplate", length = 10)
    private String licenseplate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shippingpartnerid")
    private ShippingPartner shippingpartnerid;

}