package com.mwg.tms.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RouteDetailRespone {
    String routeId;
    String startLocation;
    Date startTime;
    String endLocation;
    Date endTime;
    TypeVehicle typeVehicle;
    DeliveryPoint deliveryPoint;
}
