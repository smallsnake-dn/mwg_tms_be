package com.mwg.tms.controllers;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.mwg.tms.DTO.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mwg.tms.entities.Route;
import com.mwg.tms.services.ICarrierService;
import com.mwg.tms.services.impl.CarrierService;

@CrossOrigin
@RestController
@RequestMapping("/api/carrier")
public class CarrierController {
    private ICarrierService carrierService;

    public CarrierController(CarrierService carrierService) {
        this.carrierService = carrierService;
    }

    @PostMapping("/suggest")
    public List<Route> suggestCarrier(@RequestBody SuggestRequestDto listIdRoute) {
        try {
            List<Route> list = carrierService.suggestCarrier(listIdRoute.getData());
            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @PutMapping()
    public ResponseEntity<String> updateCarrierForRoute(@RequestBody CarrierUpdateRequestDto carrierUpdate) {
        try {
            carrierService.updateCarrierForRoute(carrierUpdate.getData());
            return ResponseEntity.ok().body("Update successs");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().body("error server");
        }
    }

    // tested
    @PostMapping("/request")
    public ResponseEntity<String> createShippingRequest(@RequestBody CreateShippingRequestDto listRouteId) {
        try {
            carrierService.createShippingRequest(listRouteId.getData());
            return ResponseEntity.ok().body("OKK");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().body("server error");
        }
    }

    // @PostMapping("/request/filter")
    // public ResponseEntity<List<CarrierRequestDto>> getListCarrierRequestByFilter(
    //         @RequestBody CarrierRequestFilterDto filter) {
    //     try {
    //         List<CarrierRequestDto> rs = carrierService.getListRequestByFilter(filter);
    //         return ResponseEntity.ok().body(rs);
    //     } catch (Exception e) {
    //     }
    //     return null;
    // }

    
    @PutMapping("/request")
    public ResponseEntity<String> updateCarrierForRequest(@RequestBody UpdateStatusDto update) {
        try {
            if(update.getData().getType() && ((update.getData().getVehicleinfo() == null) || (update.getData().getDriverinfo() == null))) {
                return ResponseEntity.badRequest().body("bad request");
            }
            carrierService.updateStatus(update.getData()

            );
            return ResponseEntity.ok().body("OKK");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("server error");
        }
    }

}
