package com.mwg.tms.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "toll_booth_costs")
public class TollBoothCost {
    @EmbeddedId
    private TollBoothCostId id;

    @MapsId("tollboothid")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tollboothid", nullable = false)
    private TollBooth tollboothid;

    @MapsId("typeofvehicleid")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "typeofvehicleid", nullable = false)
    private TypeOfVehicle typeofvehicleid;

    @Column(name = "costs")
    private Double costs;

}