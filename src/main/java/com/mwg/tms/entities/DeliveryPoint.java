package com.mwg.tms.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Getter
@Setter
@Entity
@Table(name = "delivery_point")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DeliveryPoint {
    @Id
    @Column(name = "deliverypointid", nullable = false, length = 36)
    private String id;

    @Column(name = "ordinalnumber")
    private Integer ordinalnumber;

    @Column(name = "deliverypointlocation")
    private Integer deliverypointlocation;

    @Column(name = "arrivaltime")
    private Instant arrivaltime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "routeid")
    private Route routeid;

}