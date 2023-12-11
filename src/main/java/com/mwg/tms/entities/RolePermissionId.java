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
public class RolePermissionId implements Serializable {
    private static final long serialVersionUID = -6458125557146724427L;
    @Column(name = "roleid", nullable = false)
    private Integer roleid;

    @Column(name = "permissionid", nullable = false)
    private Integer permissionid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RolePermissionId entity = (RolePermissionId) o;
        return Objects.equals(this.permissionid, entity.permissionid) &&
                Objects.equals(this.roleid, entity.roleid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(permissionid, roleid);
    }

}