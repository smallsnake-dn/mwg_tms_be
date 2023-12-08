package com.mwg.tms.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

import java.util.List;
import java.io.Serializable;


@Getter
@Setter
@Entity
@Table(name = "route")
public class Route implements Serializable{
    private static final long serialVersionUID = -5900123108359853661L;
    @Id
    @Column(name = "routeid", nullable = false)
    private Integer id;

    // @Column(name = "departurelocationid")
    // private Integer departurelocationid;

    // @Column(name = "endinglocationid")
    // private Integer endinglocationid;

    @Column(name = "routelength")
    private Double routelength;

    @Column(name = "starttime")
    private Instant starttime;

    @Column(name = "endtime")
    private Instant endtime;

    // @Column(name = "typeofvehicleid")
    // private Integer typeofvehicleid;

    @ManyToOne
    @JoinColumn(name = "typeofvehicleid")
    TypeOfVehicle typeOfVehicle;

    @ManyToOne
    @JoinColumn(name = "departurelocationid")
    PhysicalLocation departurelocation;

    @ManyToOne
    @JoinColumn(name = "endinglocationid")
    PhysicalLocation endinglocation;

    @OneToMany(mappedBy = "id.routeid")
    List<RouteShipping> routeShipping;

    @OneToMany(mappedBy = "routeid")
    List<DeliveryPoint> deliveryPoints;

}