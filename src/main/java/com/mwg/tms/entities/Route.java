package com.mwg.tms.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "route")
public class Route {
    @Id
    @Column(name = "routeid", nullable = false)
    private Integer id;

    // @Column(name = "departurelocationid")
    // private Integer departurelocationid;
    @ManyToOne
    @JoinColumn(name = "departurelocationid")
    private PhysicalLocation departurelocation;

    // @Column(name = "endinglocationid")
    // private Integer endinglocationid;
    @ManyToOne
    @JoinColumn(name = "endinglocationid")
    private PhysicalLocation endinglocation;
    
    @Column(name = "routelength")
    private Double routelength;

    @Column(name = "starttime")
    private Instant starttime;

    @Column(name = "endtime")
    private Instant endtime;

    @Column(name = "typeofvehicleid")
    private Integer typeofvehicleid;
}