package com.mwg.tms.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "driver_choice_of_transportation_partner")
public class DriverChoiceOfTransportationPartner {
    @EmbeddedId
    private DriverChoiceOfTransportationPartnerId id;

    //TODO [JPA Buddy] generate columns from DB
}