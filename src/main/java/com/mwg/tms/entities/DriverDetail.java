package com.mwg.tms.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "driver_detail")
public class DriverDetail {
    @EmbeddedId
    private DriverDetailId id;

    @MapsId("driverid")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "driverid", nullable = false)
    private Driver driverid;

}