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
    String departureLocationName;
    Instant startTime;
    String endingLocationName;
    Instant endTime;
    CarRentalInfomation carRentalInfomation;
}
