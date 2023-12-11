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
public class SuggestShippingUnitId implements Serializable {
    private static final long serialVersionUID = 1209453182693693928L;
    @Column(name = "routeid", nullable = false, length = 36)
    private String routeid;

    @Column(name = "shippingpartnerid", nullable = false, length = 36)
    private String shippingpartnerid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SuggestShippingUnitId entity = (SuggestShippingUnitId) o;
        return Objects.equals(this.routeid, entity.routeid) &&
                Objects.equals(this.shippingpartnerid, entity.shippingpartnerid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(routeid, shippingpartnerid);
    }

}