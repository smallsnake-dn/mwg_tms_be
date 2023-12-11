package com.mwg.tms.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "shipping_request_detail")
public class ShippingRequestDetail {
    @Id
    @Column(name = "shippingrequestid", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "shippingrequestid", nullable = false)
    private ShippingRequest shippingRequest;

    @Column(name = "vehicleid", length = 10)
    private String vehicleid;

    @Column(name = "status")
    private Integer status;

}