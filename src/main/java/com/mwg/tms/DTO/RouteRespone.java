package com.mwg.tms.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RouteRespone {
    String routeId;
    String departureLocationId;
    Date startTime;
    String endingLocationId;
    Date endTime;
}
