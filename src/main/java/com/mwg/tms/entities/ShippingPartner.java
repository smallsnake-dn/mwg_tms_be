package com.mwg.tms.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "shipping_partner")
public class ShippingPartner {
    @Id
    @Column(name = "shippingpartnerid", nullable = false, length = 40)
    private String shippingpartnerid;

    @Column(name = "shippingparnername", length = 50)
    private String shippingparnername;

    @Column(name = "phonenumber", length = 12)
    private String phonenumber;

    @OneToMany(mappedBy = "id.shippingpartnerid")
    List<TransportationResource> transportationresource;

    @OneToMany(mappedBy = "shippingpartnerid")
    List<ShippingServicePrice> shippingserviceprice;
}