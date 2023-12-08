package com.mwg.tms.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "delivery_point_package")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DeliveryPointPackage {
    @EmbeddedId
    private DeliveryPointPackageId id;

    @MapsId("deliverypointid")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "deliverypointid", nullable = false)
    private DeliveryPoint deliverypoint;
    
    @MapsId("packageid")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "packageid", nullable = false)
    private Package packagedetail;

    @Column(name = "behavior")
    private Integer behavior;


}