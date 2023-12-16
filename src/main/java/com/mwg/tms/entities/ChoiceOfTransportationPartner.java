package com.mwg.tms.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

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

//    @Column(name = "shippingpartnerid", length = 40)
//    private String shippingpartnerid;
    @ManyToOne
    @JoinColumn(name = "shippingpartnerid")
    ShippingPartner shippingPartner;

    @Column(name = "senderinformation", length = 20)
    private String senderinformation;

    @Column(name = "deleteat")
    private Instant deleteat;

    @Column(name = "note", length = Integer.MAX_VALUE)
    private String note;

    // @Column(name = "vehicleid", length = 40)
    // private String vehicleid;
    @ManyToOne
    @JoinColumn(name = "vehicleid")
    Vehicle vehicle;


    @OneToMany(mappedBy = "id.choiceoftransportationparterid")
    List<DriverChoiceOfTransportationPartner> listdriver;
}