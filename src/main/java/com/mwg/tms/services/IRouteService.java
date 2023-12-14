package com.mwg.tms.services;

import java.util.List;

import com.mwg.tms.DAO.IRoute;
import com.mwg.tms.DTO.RouteResponeDto;
import com.mwg.tms.DTO.PackageResponeDto;
import com.mwg.tms.DTO.RouteDetailRespone;
import com.mwg.tms.DTO.RouteRequest;
import com.mwg.tms.entities.DeliveryPointPackage;
import com.mwg.tms.entities.Route;

public interface IRouteService {
    public List<RouteResponeDto> getListRoute(RouteRequest routeRequest);
    public RouteDetailRespone getRouteDetailById(String routeId);
    public List<DeliveryPointPackage> getDetailDeliveryPoint(String deliveryId);
    public List<Route> getListRouteById(List<String> listRoute);
}
