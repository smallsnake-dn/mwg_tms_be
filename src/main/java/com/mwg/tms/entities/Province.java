package com.mwg.tms.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "province")
public class Province {
    @Id
    @Column(name = "provinceid", nullable = false)
    private Integer id;

    @Column(name = "provincename", length = 50)
    private String provincename;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "countryid")
    private Country countryid;

}