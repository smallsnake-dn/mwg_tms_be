package com.mwg.tms.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "shipping_partner")
public class ShippingPartner {
    @Id
    @Column(name = "shippingpartnerid", nullable = false)
    private Integer id;

    @Column(name = "shippingparnername", length = 50)
    private String shippingparnername;

    @Column(name = "phonenumber", length = 12)
    private String phonenumber;

}