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
public class UsersRoleId implements Serializable {
    private static final long serialVersionUID = -5241366201235870759L;
    @Column(name = "userid", nullable = false)
    private Integer userid;

    @Column(name = "roleid", nullable = false)
    private Integer roleid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UsersRoleId entity = (UsersRoleId) o;
        return Objects.equals(this.roleid, entity.roleid) &&
                Objects.equals(this.userid, entity.userid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleid, userid);
    }

}