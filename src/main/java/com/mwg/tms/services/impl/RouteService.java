package com.mwg.tms.services.impl;

import java.util.List;

import com.mwg.tms.DTO.RouteDetailDto;
import org.springframework.stereotype.Service;

import com.mwg.tms.entities.CarRentalInfomation;
import com.mwg.tms.entities.DeliveryPointPackage;
import com.mwg.tms.entities.Route;
import com.mwg.tms.repositories.ICarRentalInfomationRepository;
import com.mwg.tms.repositories.IDeliveryPointPackage;
import com.mwg.tms.repositories.IRouteRepository;
import com.mwg.tms.repositories.IRouteRepositoryCustom;
import com.mwg.tms.DAO.RouteResponeDao;
import com.mwg.tms.DTO.RouteDetailRespone;
import com.mwg.tms.DTO.RouteRequest;
import com.mwg.tms.DTO.RouteResponeDto;
import com.mwg.tms.services.IRouteService;

@Service
public class RouteService implements IRouteService {
    private IRouteRepository routeRepository;
    private IRouteRepositoryCustom routeRepositoryCustom;
    private IDeliveryPointPackage deliveryPointPackage;
    private ICarRentalInfomationRepository carRentalInfomationRepository;

    public RouteService(IRouteRepository routeRepository, IRouteRepositoryCustom routeRepositoryCustom,
            IDeliveryPointPackage deliveryPointPackage, ICarRentalInfomationRepository carRentalInfomationRepository) {
        this.routeRepository = routeRepository;
        this.routeRepositoryCustom = routeRepositoryCustom;
        this.deliveryPointPackage = deliveryPointPackage;
        this.carRentalInfomationRepository = carRentalInfomationRepository;
    }

    // private int checkStatus()
    @Override
    public List<RouteResponeDto> getListRoute(RouteRequest routeRequest) throws Exception{
        List<RouteResponeDao> routesResponeDao = routeRepositoryCustom.findAllBydeparturelocationid(routeRequest);
        if(routesResponeDao == null) {
            throw new Exception("getListRoute khong the tim kiem danh sach tuyen");
        }
        List<RouteResponeDto> routesResponeDto = RouteResponeDto.castFromRouteResponeDao(routesResponeDao);
        return routesResponeDto;
    }

    @Override
    public RouteDetailDto getRouteDetailById(String routeId) throws Exception{
        // Route route = routeRepository.getRouteById(routeId);
        Route route = routeRepository.findById(routeId).get();
        if(route == null) {
            throw new Exception("getRouteDetailById khong the tim thay thong tin tuyen");
        }
//        List<CarRentalInfomation> carRentalInfomation = carRentalInfomationRepository.findByRouteid(routeId);
        return new RouteDetailDto(route);
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
