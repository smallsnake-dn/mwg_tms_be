package com.mwg.tms.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.mwg.tms.DTO.CarrierRequestFilterDto;
import com.mwg.tms.DTO.CarrierRequestDto;
import com.mwg.tms.DTO.SuggestCarrierResponeDto;

@Service
public interface ICarrierService {
    public List<SuggestCarrierResponeDto> suggestCarrier(List<String> listRouteId);
    public String createShippingRequest(List<String> listIdRoute);
    public List<CarrierRequestDto> getListRequestByFilter(CarrierRequestFilterDto filter);
}
