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
public class TollBoothCostId implements Serializable {
    private static final long serialVersionUID = 6426587098821078230L;
    @Column(name = "tollboothid", nullable = false)
    private Integer tollboothid;

    @Column(name = "typeofvehicleid", nullable = false)
    private Integer typeofvehicleid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TollBoothCostId entity = (TollBoothCostId) o;
        return Objects.equals(this.tollboothid, entity.tollboothid) &&
                Objects.equals(this.typeofvehicleid, entity.typeofvehicleid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tollboothid, typeofvehicleid);
    }

}