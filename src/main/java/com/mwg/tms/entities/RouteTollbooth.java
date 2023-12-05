package com.mwg.tms.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "route_tollbooth")
public class RouteTollbooth {
    @EmbeddedId
    private RouteTollboothId id;

    @MapsId("routeid")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "routeid", nullable = false)
    private Route routeid;

}