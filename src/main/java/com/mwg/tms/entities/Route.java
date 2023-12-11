package com.mwg.tms.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "route")
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

}