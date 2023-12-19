package com.mwg.tms.services;

import java.util.List;

import com.mwg.tms.DTO.*;
import com.mwg.tms.entities.DeliveryPointPackage;
import com.mwg.tms.entities.Route;

public interface IRouteService {
    public List<RouteResponeDto> getListRoute(RouteRequest routeRequest) throws Exception;
    public RouteDetailDto getRouteDetailById(String routeId) throws Exception;
    public List<DeliveryPointPackage> getDetailDeliveryPoint(String deliveryId);
    public List<Route> getListRouteById(List<String> listRoute);
}
