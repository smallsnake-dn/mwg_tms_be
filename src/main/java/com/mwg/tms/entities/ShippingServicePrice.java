package com.mwg.tms.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "shipping_service_price")
public class ShippingServicePrice {
    @Id
    @Column(name = "shippingservicepriceid", nullable = false, length = 36)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shippingpartnerid")
    private ShippingPartner shippingpartnerid;

    @Column(name = "typeofvehicleid", length = 36)
    private String typeofvehicleid;

    @Column(name = "startlocationid", length = 36)
    private String startlocationid;

    @Column(name = "endlocationid", length = 36)
    private String endlocationid;

    @Column(name = "typeofservice")
    private Integer typeofservice;

    @Column(name = "typeofcostsperkm")
    private Integer typeofcostsperkm;

    @Column(name = "loadingfeecosts")
    private Double loadingfeecosts;

    @Column(name = "stoppointcosts")
    private Double stoppointcosts;

    @Column(name = "nightexpenses")
    private Double nightexpenses;

    @Column(name = "holidayexpenses")
    private Double holidayexpenses;

/*
    TODO [JPA Buddy] create field to map the 'roadandbridgecosts' column
     Available actions: Define target Java type | Uncomment as is | Remove column mapping
    @Column(name = "roadandbridgecosts", columnDefinition = "bit(0, 0)")
    private Object roadandbridgecosts;
*/
}