package com.mwg.tms.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mwg.tms.entities.DeliveryPointPackage;
import com.mwg.tms.entities.Route;
import com.mwg.tms.repositories.IDeliveryPointPackage;
import com.mwg.tms.repositories.IRouteRepository;
import com.mwg.tms.repositories.IRouteRepositoryCustom;
import com.mwg.tms.DAO.RouteResponeDao;
import com.mwg.tms.DTO.RouteRequest;
import com.mwg.tms.DTO.RouteResponeDto;
import com.mwg.tms.services.IRouteService;

@Service
public class RouteService implements IRouteService {
    private IRouteRepository routeRepository;
    private IRouteRepositoryCustom routeRepositoryCustom;
    private IDeliveryPointPackage deliveryPointPackage;

    public RouteService(IRouteRepository routeRepository, IRouteRepositoryCustom routeRepositoryCustom,
            IDeliveryPointPackage deliveryPointPackage) {
        this.routeRepository = routeRepository;
        this.routeRepositoryCustom = routeRepositoryCustom;
        this.deliveryPointPackage = deliveryPointPackage;
    }

    // private int checkStatus()
    @Override
    public List<RouteResponeDto> getListRoute(RouteRequest routeRequest) {
        List<RouteResponeDto> listRoute = routeRepositoryCustom.findAllBydeparturelocationid(routeRequest);


        return listRoute;
    }

    @Override
    public Route getRouteDetailById(String routeId) {
        // Route route = routeRepository.getRouteById(routeId);
        Route route = routeRepository.findById(routeId).get();
        return route;
    }

    @Override
    public List<DeliveryPointPackage> getDetailDeliveryPoint(String deliveryId) {
        // List<PackageResponeDto> listPackage = new ArrayList<>();
        // listPackage.add(new PackageResponeDto(0, deliveryId, deliveryId, deliveryId,
        // routeId, deliveryId));
        List<DeliveryPointPackage> list = this.deliveryPointPackage.findBydeliverypointid(deliveryId);
        // System.out.println("DeliveryPointPackage size: " + size);
        return list;
    }

    @Override
    public List<Route> getListRouteById(List<String> listRoute) {
        List<Route> list = routeRepository.findAllById(listRoute);
        return list;
    }

}
