package com.mwg.tms.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mwg.tms.DTO.CarrierRequestDto;
import com.mwg.tms.DTO.CarrierRequestFilterDto;
import com.mwg.tms.DTO.CarrierUpdateRequestDto;
import com.mwg.tms.DTO.SuggestCarrierResponeDto;
import com.mwg.tms.DTO.UpdateCarrierRequestDto;
import com.mwg.tms.services.ICarrierService;
import com.mwg.tms.services.impl.CarrierService;

@RestController
@RequestMapping("/api/carrier")
public class CarrierController {
    private ICarrierService carrierService;

    public CarrierController(CarrierService carrierService) {
        this.carrierService = carrierService;
    }

    @PostMapping("/suggest")
    public List<SuggestCarrierResponeDto> suggestCarrier(@RequestBody List<String> listIdRoute) {
        try {
            List<SuggestCarrierResponeDto> list = carrierService.suggestCarrier(listIdRoute);
            return list;
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }

    @PutMapping("/")
    public ResponseEntity<String> updateCarrierForRoute(@RequestBody CarrierUpdateRequestDto carrierUpdate) {
        return ResponseEntity.ok().body("Update successs");
    }

    @PostMapping("/request")
    public ResponseEntity<String> createShippingRequest(@RequestBody List<String> listRouteId) {
        try {
            String rs = carrierService.createShippingRequest(listRouteId);
            return ResponseEntity.ok().body(rs);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }

    @PostMapping("/request/filter")
    public ResponseEntity<List<CarrierRequestDto>> getListCarrierRequestByFilter(@RequestBody CarrierRequestFilterDto filter) {
        try {
            List<CarrierRequestDto> rs = carrierService.getListRequestByFilter(filter);
            return ResponseEntity.ok().body(rs);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }

    @PutMapping("/request")
    public ResponseEntity<String> updateCarrierRequest(@RequestBody UpdateCarrierRequestDto update) {

        return null;
    }

}
