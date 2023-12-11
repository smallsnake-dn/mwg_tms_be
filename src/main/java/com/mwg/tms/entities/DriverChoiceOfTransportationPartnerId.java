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
public class DriverChoiceOfTransportationPartnerId implements Serializable {
    private static final long serialVersionUID = 8142435812395277510L;
    @Column(name = "driverid", nullable = false)
    private Integer driverid;

    @Column(name = "choiceoftransportationparterid", nullable = false)
    private Integer choiceoftransportationparterid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DriverChoiceOfTransportationPartnerId entity = (DriverChoiceOfTransportationPartnerId) o;
        return Objects.equals(this.choiceoftransportationparterid, entity.choiceoftransportationparterid) &&
                Objects.equals(this.driverid, entity.driverid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(choiceoftransportationparterid, driverid);
    }

}