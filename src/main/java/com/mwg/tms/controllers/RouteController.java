package com.mwg.tms.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mwg.tms.DTO.PackageResponeDto;
import com.mwg.tms.DTO.RouteDetailRespone;
import com.mwg.tms.DTO.RouteRequest;
import com.mwg.tms.DTO.RouteRespone;
import com.mwg.tms.services.IRouteServicce;
import com.mwg.tms.services.impl.RouteService;

@RestController
@RequestMapping("/api/route")
public class RouteController {
    private IRouteServicce routeService;
    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping()
    public ResponseEntity<List<RouteRespone>> getListRoute(@RequestBody RouteRequest routeRequest) {
         try {
            List<RouteRespone> listRoute = routeService.getListRoute(routeRequest);
            System.out.println("OKKK");
            return ResponseEntity.ok().body(listRoute);
         } catch (Exception e) {
            // TODO: handle exception
         }
        return null;
    }

    @GetMapping("/{routeId}")
    public ResponseEntity<RouteDetailRespone> getRouteDetailById(@PathVariable(name = "routeId") String routeId) {
        try {
            RouteDetailRespone route = routeService.getRouteDetailById(routeId);
            return ResponseEntity.ok().body(route);
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }

    @GetMapping("/{routeId}/{deliveryId}")
    public List<PackageResponeDto> getDetailDeliveryPoint(@PathVariable("routeId") String routeId, @PathVariable("deliveryId") String deliveryId) {
        List<PackageResponeDto> list = routeService.getDetailDeliveryPoint(routeId, deliveryId); 
        return list;
    }
}
