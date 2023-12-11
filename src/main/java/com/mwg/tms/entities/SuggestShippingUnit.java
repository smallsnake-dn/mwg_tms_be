package com.mwg.tms.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "suggest_shipping_unit")
public class SuggestShippingUnit {
    @EmbeddedId
    private SuggestShippingUnitId id;

    @MapsId("routeid")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "routeid", nullable = false)
    private Route routeid;

    @MapsId("shippingpartnerid")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "shippingpartnerid", nullable = false)
    private ShippingPartner shippingpartnerid;

    @Column(name = "cost")
    private Double cost;

    @Column(name = "status", length = 10)
    private String status;

}