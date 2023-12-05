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
@Table(name = "delivery_point")
public class DeliveryPoint {
    @Id
    @Column(name = "deliverypointid", nullable = false)
    private Integer id;

    @Column(name = "ordinalnumber")
    private Integer ordinalnumber;

    @Column(name = "deliverypointlocation")
    private Integer deliverypointlocation;

    @Column(name = "arrivaltime")
    private Instant arrivaltime;

}