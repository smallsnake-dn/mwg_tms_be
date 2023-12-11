package com.mwg.tms.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "driver_choice_of_transportation_partner")
public class DriverChoiceOfTransportationPartner {
    @EmbeddedId
    private DriverChoiceOfTransportationPartnerId id;

    @MapsId("driverid")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "driverid", nullable = false)
    private Driver driverid;

    @MapsId("choiceoftransportationparterid")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "choiceoftransportationparterid", nullable = false)
    private ChoiceOfTransportationPartner choiceoftransportationparterid;

}