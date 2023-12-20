package com.mwg.tms.controllers;

import java.util.List;

import com.mwg.tms.DTO.*;
import com.mwg.tms.utils.Respone;
import com.mwg.tms.utils.ResponeCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mwg.tms.entities.DeliveryPointPackage;
import com.mwg.tms.services.IRouteService;
import com.mwg.tms.services.impl.RouteService;

@CrossOrigin
@RestController
@RequestMapping("/api/route")
public class RouteController {
    private IRouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @PostMapping()
    public ResponseEntity<Respone<List<RouteResponeDto>>> getListRoute(@RequestBody RouteRequest routeRequest) {
        try {
            List<RouteResponeDto> listRoute = routeService.getListRoute(routeRequest);
            return ResponseEntity.ok().body(new Respone<>(
                    ResponeCode.SUCCESS.code,
                    "Thanh cong",
                    listRoute
            ));
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().body(new Respone<>(
                    ResponeCode.ERROR.code,
                    "That bai",
                    null
            ));
        }
    }

    @GetMapping("/{routeId}")
    public ResponseEntity<Respone<RouteDetailDto>> getRouteDetailById(@PathVariable(name = "routeId") String routeId) {
        try {
            RouteDetailDto route = routeService.getRouteDetailById(routeId);
            return ResponseEntity.ok().body(new Respone<>(
                    ResponeCode.SUCCESS.code,
                    "Thanh cong",
                    route
            ));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().body(new Respone<>(
                    ResponeCode.ERROR.code,
                    "That bai",
                    null
            ));
        }
    }

    @GetMapping("/delivery/{deliveryId}")
    public ResponseEntity<Respone<List<DeliveryPointPackage>>> getDetailDeliveryPoint(
            @PathVariable("deliveryId") String deliveryId) {
        List<DeliveryPointPackage> list = routeService.getDetailDeliveryPoint(deliveryId);
        return ResponseEntity.ok().body(new Respone<>(
                ResponeCode.SUCCESS.code,
                "Thanh cong",
                list
        ));
    }
}
