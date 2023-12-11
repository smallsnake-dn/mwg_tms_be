package com.mwg.tms.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mwg.tms.DAO.IRoute;
import com.mwg.tms.DTO.PackageResponeDto;
import com.mwg.tms.DTO.RouteDetailRespone;
import com.mwg.tms.DTO.RouteRequest;
import com.mwg.tms.DTO.RouteRespone;
import com.mwg.tms.entities.DeliveryPointPackage;
import com.mwg.tms.entities.Route;
import com.mwg.tms.services.IRouteService;
import com.mwg.tms.services.impl.RouteService;
import com.mwg.tms.utils.QueryBuilder;

@RestController
@RequestMapping("/api/route")
public class RouteController {
    private IRouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping()
    public ResponseEntity<List<RouteRespone>> getListRoute(@RequestBody RouteRequest routeRequest) {
        try {
            List<RouteRespone> listRoute = routeService.getListRoute(routeRequest);
            return ResponseEntity.ok().body(listRoute);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
        return null;
    }

    @GetMapping("/{routeId}")
    public ResponseEntity<Route> getRouteDetailById(@PathVariable(name = "routeId") int routeId) {
        try {
            Route route = routeService.getRouteDetailById(routeId);
            return ResponseEntity.ok().body(route);

        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }

    @GetMapping("/delivery/{deliveryId}")
    public List<DeliveryPointPackage> getDetailDeliveryPoint(
            @PathVariable("deliveryId") int deliveryId) {
        List<DeliveryPointPackage> list = routeService.getDetailDeliveryPoint(deliveryId);
        return list;
    }
}
