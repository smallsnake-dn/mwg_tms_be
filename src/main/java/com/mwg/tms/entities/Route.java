package com.mwg.tms.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Getter
@Setter
@Entity
@Table(name = "route")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Route {
    @Id
    @Column(name = "routeid", nullable = false, length = 40)
    private String routeid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departurelocationid")
    private PhysicalLocation departurelocation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "endinglocationid")
    private PhysicalLocation endinglocation;

    @Column(name = "routelength")
    private Double routelength;

    @Column(name = "starttime")
    private Instant starttime;

    @Column(name = "endtime")
    private Instant endtime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "typeofvehicleid")
    private TypeOfVehicle typeofvehicleid;

    @OneToMany(mappedBy = "routeid")
    List<DeliveryPoint> listdeliverypoint;

    @OneToMany(mappedBy = "routeid")
    List<CarRentalInfomation> listCarRentalInfomations;
}