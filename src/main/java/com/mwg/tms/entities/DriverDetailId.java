package com.mwg.tms.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class DriverDetailId implements Serializable {
    private static final long serialVersionUID = -5900364108359853661L;
    @Column(name = "shippingrequestid", nullable = false, length = 36)
    private String shippingrequestid;

    @Column(name = "driverid", nullable = false, length = 36)
    private String driverid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DriverDetailId entity = (DriverDetailId) o;
        return Objects.equals(this.driverid, entity.driverid) &&
                Objects.equals(this.shippingrequestid, entity.shippingrequestid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(driverid, shippingrequestid);
    }

}