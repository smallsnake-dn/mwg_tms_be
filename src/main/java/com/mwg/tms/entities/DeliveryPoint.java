package com.mwg.tms.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "delivery_point")
public class DeliveryPoint {
    @Id
    @Column(name = "deliverypointid", nullable = false, length = 40)
    private String deliverypointid;

    @Column(name = "ordinalnumber")
    private Integer ordinalnumber;

    @Column(name = "deliverypointlocation", length = 40)
    private String deliverypointlocation;

    @Column(name = "arrivaltime")
    private Instant arrivaltime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "routeid")
    private Route routeid;

}