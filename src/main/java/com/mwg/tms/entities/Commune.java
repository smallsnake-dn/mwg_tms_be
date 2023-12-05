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
@Table(name = "commune")
public class Commune {
    @Id
    @Column(name = "communeid", nullable = false)
    private Integer id;

    @Column(name = "communename", length = 50)
    private String communename;

}