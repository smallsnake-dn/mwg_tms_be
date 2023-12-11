package com.mwg.tms.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mwg.tms.DTO.CarrierRequestFilterDto;
import com.mwg.tms.DTO.CarrierUpdateRequestDto;
import com.mwg.tms.DTO.CarrierRequestDto;
import com.mwg.tms.DTO.UpdateStatusDto;
import com.mwg.tms.entities.Route;

@Service
public interface ICarrierService {
    public List<Route> suggestCarrier(List<String> listRouteId);
    public void createShippingRequest(List<String> listIdRoute) throws Exception;
    public List<CarrierRequestDto> getListRequestByFilter(CarrierRequestFilterDto filter);
    public void updateStatus(UpdateStatusDto update) throws Exception;
    public void updateCarrierForRoute(CarrierUpdateRequestDto update) throws Exception;
}
