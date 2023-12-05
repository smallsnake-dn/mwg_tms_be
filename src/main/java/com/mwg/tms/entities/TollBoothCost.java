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

    @Column(name = "costs")
    private Double costs;

}