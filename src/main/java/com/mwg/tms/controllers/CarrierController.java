package com.mwg.tms.controllers;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.mwg.tms.DTO.*;
import com.mwg.tms.utils.Respone;
import com.mwg.tms.utils.ResponeCode;
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
    public ResponseEntity<Respone<List<String>>> suggestCarrier(@RequestBody SuggestRequestDto listIdRoute) {
        try {
            CarrierService.SuggestCarrierRespone response = carrierService.suggestCarrier(listIdRoute.getData());
            if(response.getListErr().isEmpty()) {
                return ResponseEntity.ok().body(new Respone<>(
                        ResponeCode.SUCCESS.code,
                        "Thanh cong",
                        null
                ));
            } else {
                return ResponseEntity.ok().body(new Respone<>(
                        ResponeCode.ERROR.code,
                        "Co tuyen khong the de xuat",
                        response.getListErr()
                ));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.ok().body(new Respone<>(
                    ResponeCode.ERROR.code,
                    "That bai",
                    null
            ));
        }
    }

//    public ResponseEntity<> getListCarrierOfRoute() {
//
//        return ResponseEntity.ok().body(null);
//    }

    @PutMapping()
    public ResponseEntity<Respone<?>> updateCarrierForRoute(@RequestBody CarrierUpdateRequestDto carrierUpdate) {
        try {
            carrierService.updateCarrierForRoute(carrierUpdate.getData());
            return ResponseEntity.ok().body(new Respone<>(
                    ResponeCode.SUCCESS.code,
                    "Thanh cong",
                    null
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

    // tested
//    @PostMapping("/request")
//    public ResponseEntity<String> createShippingRequest(@RequestBody CreateShippingRequestDto listRouteId) {
//        try {
//            carrierService.createShippingRequest(listRouteId.getData());
//            return ResponseEntity.ok().body("OKK");
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            return ResponseEntity.internalServerError().body("server error");
//        }
//    }

    @PostMapping("/request")
    public ResponseEntity<Respone<?>> createShippingRequest(@RequestBody CreateShippingRequestDto listRouteId) {
        try {
            carrierService.createShippingRequest(listRouteId.getData());
            return ResponseEntity.ok().body(new Respone<>(
                    ResponeCode.SUCCESS.code,
                    "Thanh cong",
                    null
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
    public ResponseEntity<Respone<?>> updateCarrierForRequest(@RequestBody UpdateStatusDto update) {
        try {
            if (update.getData().getType() && ((update.getData().getVehicleinfo() == null) || (update.getData().getDriverinfo() == null))) {
                return ResponseEntity.badRequest().body(new Respone<>(
                        ResponeCode.ERROR.code,
                        "Bad request",
                        null
                ));
            }
            carrierService.updateStatus(update.getData());
            return ResponseEntity.ok().body(new Respone<>(
                    ResponeCode.SUCCESS.code,
                    "Thanh cong",
                    null
            ));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new Respone<>(
                    ResponeCode.ERROR.code,
                    "That bai",
                    null
            ));
        }
    }


    @PostMapping("/request/filter")
    public ResponseEntity<Respone<List<RequestResponeDto>>> getListRequest(@RequestBody RouteRequest request) {
        try {
            List<RequestResponeDto> list = carrierService.getListRequest(request);
            return ResponseEntity.ok().body(new Respone<List<RequestResponeDto>>(
                    ResponeCode.SUCCESS.code,
                    "Thanh cong",
                    list
            ));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.ok().body(new Respone<List<RequestResponeDto>>(
                    ResponeCode.ERROR.code,
                    "That bai",
                    null
            ));
        }
    }

    @GetMapping("/info")
    public ResponseEntity<Respone<List<ShippingPartnerRespone>>> getShippingPartner() {
        List<ShippingPartnerRespone> list = carrierService.getListCarrier();
        return ResponseEntity.ok().body(new Respone<>(
                ResponeCode.SUCCESS.code,
                "Thanh cong",
                list
        ));
    }

}
