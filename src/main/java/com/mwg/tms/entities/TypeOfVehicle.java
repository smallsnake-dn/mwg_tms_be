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
@Table(name = "type_of_vehicle")
public class TypeOfVehicle {
    @Id
    @Column(name = "typeofvehicelid", nullable = false)
    private Integer id;

    @Column(name = "payload")
    private Double payload;

    @Column(name = "volume")
    private Double volume;

    @Column(name = "feature")
    private Integer feature;

    @Column(name = "costperkm")
    private Double costperkm;

}