package com.mwg.tms.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "district")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class District {
    @Id
    @Column(name = "districtid", nullable = false)
    private Integer id;

    @Column(name = "districtname", length = 50)
    private String districtname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provinceid")
    private Province provinceid;

}