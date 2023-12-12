package com.mwg.tms.DAO;

import java.util.Date;

import com.mwg.tms.entities.CarRentalInfomation;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.Instant;

@Data
@AllArgsConstructor
public class RouteResponeDao {
    String routeId;
    String departureLocationId;
    Instant startTime;
    String endingLocationId;
    Instant endTime;
    CarRentalInfomation carRentalInfomation;
}
