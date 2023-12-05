package com.mwg.tms.services;

import java.util.List;

import com.mwg.tms.DTO.PackageResponeDto;
import com.mwg.tms.DTO.RouteDetailRespone;
import com.mwg.tms.DTO.RouteRequest;
import com.mwg.tms.DTO.RouteRespone;

public interface IRouteServicce {
    public List<RouteRespone> getListRoute(RouteRequest routeRequest);
    public RouteDetailRespone getRouteDetailById(String routeId);
    public List<PackageResponeDto> getDetailDeliveryPoint(String routeId, String deliveryId);
}
