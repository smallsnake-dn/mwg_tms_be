package com.mwg.tms.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "physical_location")
public class PhysicalLocation implements Serializable{
    private static final long serialVersionUID = -5900364108312313123L;
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