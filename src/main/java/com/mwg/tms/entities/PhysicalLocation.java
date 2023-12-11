package com.mwg.tms.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "physical_location")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PhysicalLocation {
    @Id
    @Column(name = "locationid", nullable = false, length = 40)
    private String locationid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "countryid")
    private Country countryid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provinceid")
    private Province provinceid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "districtid")
    private District districtid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "communeid")
    private Commune communeid;

    @Column(name = "exactaddress", length = 50)
    private String exactaddress;

}