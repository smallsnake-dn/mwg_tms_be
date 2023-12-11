package com.mwg.tms.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "package")
public class Package {
    @Id
    @Column(name = "packageid", nullable = false)
    private Integer id;

    @Column(name = "departurelocation")
    private Integer departurelocation;

    @Column(name = "endinglocation")
    private Integer endinglocation;

    @Column(name = "senderaddress", length = 50)
    private String senderaddress;

    @Column(name = "sendername", length = 50)
    private String sendername;

    @Column(name = "senderphone", length = 12)
    private String senderphone;

    @Column(name = "receiveraddress", length = 50)
    private String receiveraddress;

    @Column(name = "receivername", length = 50)
    private String receivername;

    @Column(name = "receiverphone", length = 12)
    private String receiverphone;

    @Column(name = "mass")
    private Double mass;

    @Column(name = "volume")
    private Double volume;

    @Column(name = "deliverytime")
    private Instant deliverytime;

    @Column(name = "typeofgoods")
    private Integer typeofgoods;

}