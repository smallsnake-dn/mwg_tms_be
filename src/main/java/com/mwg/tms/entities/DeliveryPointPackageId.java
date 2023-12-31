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
public class DeliveryPointPackageId implements Serializable {
    private static final long serialVersionUID = -5197204165127244184L;
    @Column(name = "deliverypointid", nullable = false, length = 40)
    private String deliverypointid;

    @Column(name = "packageid", nullable = false, length = 40)
    private String packageid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DeliveryPointPackageId entity = (DeliveryPointPackageId) o;
        return Objects.equals(this.deliverypointid, entity.deliverypointid) &&
                Objects.equals(this.packageid, entity.packageid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deliverypointid, packageid);
    }

}