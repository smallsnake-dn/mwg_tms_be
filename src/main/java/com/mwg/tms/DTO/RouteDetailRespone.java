package com.mwg.tms.DTO;

import java.util.Date;
import java.time.Instant;

import com.mwg.tms.entities.PhysicalLocation;
import com.mwg.tms.entities.TypeOfVehicle;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import com.mwg.tms.entities.DeliveryPoint;

@Data
@AllArgsConstructor
public class RouteDetailRespone {
    int routeId;
    PhysicalLocation startLocation;
    Instant startTime;
    PhysicalLocation endLocation;
    Instant endTime;
    TypeOfVehicle typeVehicle;
    List<DeliveryPoint> deliveryPoints;
}
