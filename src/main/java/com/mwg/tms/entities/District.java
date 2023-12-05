package com.mwg.tms.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "district")
public class District {
    @Id
    @Column(name = "districtid", nullable = false)
    private Integer id;

    @Column(name = "countryname", length = 50)
    private String countryname;

}