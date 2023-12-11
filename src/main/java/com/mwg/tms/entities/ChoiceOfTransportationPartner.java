package com.mwg.tms.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "choice_of_transportation_partner")
public class ChoiceOfTransportationPartner {
    @Id
    @Column(name = "choiceoftransportationpartnerid", nullable = false, length = 40)
    private String choiceoftransportationpartnerid;

    @Column(name = "carrentalinformationid", length = 40)
    private String carrentalinformationid;

    @Column(name = "shippingpartnerid", length = 40)
    private String shippingpartnerid;

    @Column(name = "senderinformation", length = 20)
    private String senderinformation;

    @Column(name = "deleteat")
    private Instant deleteat;

    @Column(name = "note", length = Integer.MAX_VALUE)
    private String note;

    @Column(name = "vehicleid", length = 40)
    private String vehicleid;

}