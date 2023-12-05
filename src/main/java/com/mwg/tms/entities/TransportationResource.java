package com.mwg.tms.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "transportation_resources")
public class TransportationResource {
    @EmbeddedId
    private TransportationResourceId id;

    @MapsId("shippingpartnerid")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "shippingpartnerid", nullable = false)
    private ShippingPartner shippingpartnerid;

    @MapsId("locationid")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "locationid", nullable = false)
    private PhysicalLocation locationid;

    @Column(name = "numberofvehicle")
    private Integer numberofvehicle;

}