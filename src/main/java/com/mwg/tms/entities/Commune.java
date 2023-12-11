package com.mwg.tms.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "districtid")
    private District districtid;

}