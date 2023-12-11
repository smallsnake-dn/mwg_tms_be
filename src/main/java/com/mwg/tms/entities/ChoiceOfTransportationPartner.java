package com.mwg.tms.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "choice_of_transportation_partner")
public class ChoiceOfTransportationPartner {
    @Id
    @Column(name = "choiceoftransportationpartnerid", nullable = false, length = 36)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carrentalinformationid")
    private CarRentalInfomation carrentalinformationid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shippingpartnerid")
    private ShippingPartner shippingpartnerid;

    @Column(name = "senderinformation", length = 20)
    private String senderinformation;

    @Column(name = "deleteat")
    private Instant deleteat;

    @Column(name = "note", length = Integer.MAX_VALUE)
    private String note;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicleid")
    private Vehicle vehicleid;

}