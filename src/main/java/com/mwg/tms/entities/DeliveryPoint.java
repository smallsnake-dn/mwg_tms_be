package com.mwg.tms.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    @Column(name = "deliverypointid", nullable = false)
    private Integer id;

    @Column(name = "ordinalnumber")
    private Integer ordinalnumber;

    @Column(name = "deliverypointlocation")
    private Integer deliverypointlocation;

    @Column(name = "arrivaltime")
    private Instant arrivaltime;

}