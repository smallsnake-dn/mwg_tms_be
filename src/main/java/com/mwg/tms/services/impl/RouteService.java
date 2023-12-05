package com.mwg.tms.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mwg.tms.DTO.RouteDetailRespone;
import com.mwg.tms.DTO.TypeVehicle;
import com.mwg.tms.repositories.IRouteRepository;
import com.mwg.tms.DTO.DeliveryPoint;
import com.mwg.tms.DTO.PackageResponeDto;
import com.mwg.tms.DTO.RouteRequest;
import com.mwg.tms.DTO.RouteRespone;
import com.mwg.tms.services.IRouteServicce;

@Service
public class RouteService implements IRouteServicce {
    private IRouteRepository routeRepository;
    public RouteService (IRouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    @Override
    public List<RouteRespone> getListRoute(RouteRequest routeRequest) {
        // TODO Auto-generated method stub
        RouteRespone route = new RouteRespone("ID123", "HCM", new Date(), "DN", new Date());
        List<RouteRespone> listRoute = new ArrayList<>();
        listRoute.add(route);
        return listRoute;
    }

    @Override
    public RouteDetailRespone getRouteDetailById(String routeId) {
        // TODO Auto-generated method stub
        RouteDetailRespone route = new RouteDetailRespone(
            "ID123", "TPHCM", new Date(), "DN", new Date(), new TypeVehicle(routeId, 0, 0, routeId),
            new DeliveryPoint(0, routeId, routeId, null)
        );
        return route;
    }

    @Override
    public List<PackageResponeDto> getDetailDeliveryPoint(String routeId, String deliveryId) {
        // TODO Auto-generated method stub
        // PackageResponeDto package = new PackageResponeDto(0, deliveryId, deliveryId, deliveryId, routeId, deliveryId);
        List<PackageResponeDto> listPackage = new ArrayList<>();
        listPackage.add(new PackageResponeDto(0, deliveryId, deliveryId, deliveryId, routeId, deliveryId));
        return listPackage;
    }
    
}
