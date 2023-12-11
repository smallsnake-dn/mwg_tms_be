package com.mwg.tms.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "shipping_request")
public class ShippingRequest {
    @Id
    @Column(name = "shippingrequestid", nullable = false, length = 36)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "routeid")
    private Route routeid;

    @Column(name = "timecreate")
    private Instant timecreate;

    @Column(name = "status")
    private Integer status;

}