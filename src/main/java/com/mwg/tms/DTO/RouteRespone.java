package com.mwg.tms.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.Instant;

@Data
@AllArgsConstructor
public class RouteRespone {
    String routeId;
    String departureLocationId;
    Instant startTime;
    String endingLocationId;
    Instant endTime;
}
