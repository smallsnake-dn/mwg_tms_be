package com.mwg.tms.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "delivery_point_package")
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
    private Package packageid;

    @Column(name = "behavior")
    private Integer behavior;

}