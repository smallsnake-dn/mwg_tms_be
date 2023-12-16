package com.mwg.tms.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TypeOfVehicle {
    @Id
    @Column(name = "typeofvehicelid", nullable = false, length = 40)
    private String typeofvehicelid;

    @Column(name = "payload")
    private Double payload;

    @Column(name = "typeofvehiclename", length = 50)
    private String typeofvehiclename;

    @Column(name = "volume")
    private Double volume;

    @Column(name = "feature")
    private Integer feature;

    @Column(name = "costperkm")
    private Double costperkm;

}