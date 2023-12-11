package com.mwg.tms.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "car_rental_infomation")
public class CarRentalInfomation {
    @Id
    @Column(name = "carrentalinformationid", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "routeid")
    private Route routeid;

    @Column(name = "timecreate")
    private Instant timecreate;

    @Column(name = "approverinformation", length = 20)
    private String approverinformation;

    @Column(name = "approvaldate")
    private Instant approvaldate;

    @Column(name = "status", length = 10)
    private String status;

    @Column(name = "vehicleid")
    private Integer vehicleid;

}