package com.mwg.tms.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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

    @Column(name = "departurelocationid")
    private Integer departurelocationid;

    @Column(name = "endinglocationid")
    private Integer endinglocationid;

    @Column(name = "routelength")
    private Double routelength;

    @Column(name = "starttime")
    private Instant starttime;

    @Column(name = "endtime")
    private Instant endtime;

    @Column(name = "typeofvehicleid")
    private Integer typeofvehicleid;

}