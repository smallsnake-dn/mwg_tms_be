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
public class TransportationResourceId implements Serializable {
    private static final long serialVersionUID = 2982029720771681696L;
    @Column(name = "shippingpartnerid", nullable = false)
    private Integer shippingpartnerid;

    @Column(name = "locationid", nullable = false)
    private Integer locationid;

    @Column(name = "typeofvehicleid", nullable = false)
    private Integer typeofvehicleid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TransportationResourceId entity = (TransportationResourceId) o;
        return Objects.equals(this.shippingpartnerid, entity.shippingpartnerid) &&
                Objects.equals(this.typeofvehicleid, entity.typeofvehicleid) &&
                Objects.equals(this.locationid, entity.locationid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shippingpartnerid, typeofvehicleid, locationid);
    }

}