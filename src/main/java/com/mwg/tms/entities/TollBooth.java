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
@Table(name = "toll_booth")
public class TollBooth {
    @Id
    @Column(name = "tollboothid", nullable = false, length = 36)
    private String id;

    @Column(name = "tollboothname", length = 50)
    private String tollboothname;

}