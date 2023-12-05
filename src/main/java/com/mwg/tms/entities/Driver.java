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
    @Column(name = "driverid", nullable = false, length = 12)
    private String driverid;

    @Column(name = "drivername", length = 50)
    private String drivername;

    @Column(name = "driverphonenumber", length = 12)
    private String driverphonenumber;

}