package com.mwg.tms.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Getter
@Setter
@Entity
@Table(name = "car_rental_infomation")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CarRentalInfomation {
    @Id
    @Column(name = "carrentalinformationid", nullable = false, length = 40)
    private String carrentalinformationid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "routeid")
    private Route routeid;

    @Column(name = "timecreate")
    private Instant timecreate;

    @Column(name = "approverinformation", length = 20)
    private String approverinformation;

    @Column(name = "approvaldate")
    private Instant approvaldate;

    @Column(name = "status")
    private Integer status;

    @Column(name = "vehicleid", length = 40)
    private String vehicleid;

    @OneToMany(mappedBy = "carrentalinformationid")
    List<ChoiceOfTransportationPartner> listCOTP;
}