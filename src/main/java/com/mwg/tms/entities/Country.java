package com.mwg.tms.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Getter
@Setter
@Entity
@Table(name = "country")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Country implements Serializable{
    private static final long serialVersionUID = -5900364108123153661L;
    @Id
    @Column(name = "countryid", nullable = false)
    private Integer id;

    @Column(name = "countryname", length = 50)
    private String countryname;

}