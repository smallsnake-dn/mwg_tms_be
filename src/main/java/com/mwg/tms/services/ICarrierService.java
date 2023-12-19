package com.mwg.tms.services;

import java.util.List;

import com.mwg.tms.services.impl.CarrierService;
import org.springframework.stereotype.Service;

import com.mwg.tms.DTO.CarrierRequestFilterDto;
import com.mwg.tms.DTO.CarrierUpdateRequestDto;
import com.mwg.tms.DTO.CarrierRequestDto;
import com.mwg.tms.DTO.UpdateStatusDto;
import com.mwg.tms.entities.Route;

@Service
public interface ICarrierService {
    public CarrierService.SuggestCarrierRespone suggestCarrier(List<String> listRouteId) throws Exception;
    public void createShippingRequest(List<String> listIdRoute) throws Exception;
    public List<CarrierRequestDto> getListRequestByFilter(CarrierRequestFilterDto filter);
    public void updateStatus(UpdateStatusDto.UpdateStatus update) throws Exception;
    public void updateCarrierForRoute(CarrierUpdateRequestDto.CarrierUpdate update) throws Exception;
}
