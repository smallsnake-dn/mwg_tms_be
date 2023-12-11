package com.mwg.tms.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class RouteShippingId implements Serializable {
    private static final long serialVersionUID = 2029262175876391622L;
    @Column(name = "routeid", nullable = false)
    private Integer routeid;

    @Column(name = "shippingpartnerid", nullable = false)
    private Integer shippingpartnerid;

    @Column(name = "timecreate", nullable = false)
    private Instant timecreate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RouteShippingId entity = (RouteShippingId) o;
        return Objects.equals(this.routeid, entity.routeid) &&
                Objects.equals(this.shippingpartnerid, entity.shippingpartnerid) &&
                Objects.equals(this.timecreate, entity.timecreate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(routeid, shippingpartnerid, timecreate);
    }

}