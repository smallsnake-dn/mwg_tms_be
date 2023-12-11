package com.mwg.tms.DTO;

import java.util.Date;

import lombok.Data;

@Data
public class RouteRequest {
    Date fromDate;
    Date endDate;
    Location location;
    String status;
}
