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
@Table(name = "driver")
public class Driver {
    @Id
    
    @Column(name = "driverid", nullable = false, length = 40)
    private String driverid;

    @Column(name = "citizenidentificationcard", length = 12)
    private String citizenidentificationcard;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "phone", length = 12)
    private String phone;

    @Column(name = "driverlicenseclass", length = 1)
    private String driverlicenseclass;

    @Column(name = "shippingpartnerid", length = 40)
    private String shippingpartnerid;

}