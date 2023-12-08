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
@Table(name = "commune")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Commune {
    @Id
    @Column(name = "communeid", nullable = false)
    private Integer id;

    @Column(name = "communename", length = 50)
    private String communename;

}