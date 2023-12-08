package com.mwg.tms.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "route_shipping")
public class RouteShipping {
    @EmbeddedId
    private RouteShippingId id;

    // @MapsId("routeid")
    // @ManyToOne(fetch = FetchType.LAZY, optional = false)
    // @JoinColumn(name = "routeid", nullable = false)
    // private Route routeid;

    @Column(name = "creator")
    private Integer creator;

    @Column(name = "costs")
    private Double costs;

    @Column(name = "status")
    private Integer status;

    @Column(name = "reason", length = 50)
    private String reason;

}