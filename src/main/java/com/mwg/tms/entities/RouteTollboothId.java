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
public class RouteTollboothId implements Serializable {
    private static final long serialVersionUID = 7256758933367688893L;
    @Column(name = "tollboothid", nullable = false, length = 36)
    private String tollboothid;

    @Column(name = "routeid", nullable = false, length = 36)
    private String routeid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RouteTollboothId entity = (RouteTollboothId) o;
        return Objects.equals(this.tollboothid, entity.tollboothid) &&
                Objects.equals(this.routeid, entity.routeid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tollboothid, routeid);
    }

}