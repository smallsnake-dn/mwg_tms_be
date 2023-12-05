package com.mwg.tms.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "physical_location")
public class PhysicalLocation {
    @Id
    @Column(name = "locationid", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "countryid")
    private Country countryid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "districtid")
    private District districtid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "communeid")
    private Commune communeid;

    @Column(name = "exactaddress", length = 50)
    private String exactaddress;

}