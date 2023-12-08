package com.mwg.tms.DAO;

import java.util.Date;

public interface IRoute {
    String getRouteId();
    String getDepartureLocation();
    Date getStartTime();
    String getEndingLocation();
    Date getEndTime();
}
