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
@Table(name = "users")
public class User {
    @Id
    @Column(name = "userid", nullable = false, length = 40)
    private String userid;

    @Column(name = "username", length = 50)
    private String username;

    @Column(name = "pass", length = 50)
    private String pass;

}