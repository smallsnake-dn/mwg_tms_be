package com.mwg.tms.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "role_permission")
public class RolePermission {
    @EmbeddedId
    private RolePermissionId id;

    @MapsId("roleid")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "roleid", nullable = false)
    private Role roleid;

    @MapsId("permissionid")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "permissionid", nullable = false)
    private Permission permissionid;

}