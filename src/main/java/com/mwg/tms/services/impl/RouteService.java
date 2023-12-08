package com.mwg.tms.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.impl.IOFileUploadException;
import org.springframework.stereotype.Service;

import com.mwg.tms.DTO.RouteDetailRespone;
import com.mwg.tms.DTO.TypeVehicle;
import com.mwg.tms.entities.DeliveryPointPackage;
import com.mwg.tms.entities.Route;
import com.mwg.tms.repositories.IDeliveryPointPackage;
import com.mwg.tms.repositories.IRouteRepository;
import com.mwg.tms.repositories.IRouteRepositoryCustom;
import com.mwg.tms.DAO.IRoute;
import com.mwg.tms.DTO.DeliveryPoint;
import com.mwg.tms.DTO.PackageResponeDto;
import com.mwg.tms.DTO.RouteRequest;
import com.mwg.tms.DTO.RouteRespone;
import com.mwg.tms.services.IRouteServicce;
import com.mwg.tms.utils.QueryBuilder;

@Service
public class RouteService implements IRouteServicce {
    private IRouteRepository routeRepository;
    private IRouteRepositoryCustom routeRepositoryCustom;
    private IDeliveryPointPackage deliveryPointPackage;
    public RouteService (IRouteRepository routeRepository, IRouteRepositoryCustom routeRepositoryCustom, IDeliveryPointPackage deliveryPointPackage) {
        this.routeRepository = routeRepository;
        this.routeRepositoryCustom = routeRepositoryCustom;
        this.deliveryPointPackage = deliveryPointPackage;
    }

    @Override
    public List<RouteRespone> getListRoute(RouteRequest routeRequest) {
        List<RouteRespone> listRoute = routeRepositoryCustom.findAllBydeparturelocationid(routeRequest);
        return listRoute;
    }

    @Override
    public Route getRouteDetailById(int routeId) {
        Route route = routeRepository.getRouteById(routeId);
        return route;
    }

    @Override
    public List<DeliveryPointPackage> getDetailDeliveryPoint(int deliveryId) {
        // List<PackageResponeDto> listPackage = new ArrayList<>();
        // listPackage.add(new PackageResponeDto(0, deliveryId, deliveryId, deliveryId, routeId, deliveryId));
        List<DeliveryPointPackage> list = this.deliveryPointPackage.findBydeliverypointid(deliveryId);
        // System.out.println("DeliveryPointPackage size: " + size);
        return list;
    }
    
}
