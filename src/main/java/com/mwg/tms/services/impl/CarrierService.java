package com.mwg.tms.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mwg.tms.DTO.CarrierRequestDto;
import com.mwg.tms.DTO.CarrierRequestFilterDto;
import com.mwg.tms.DTO.SuggestCarrierResponeDto;
import com.mwg.tms.services.ICarrierService;

@Service
public class CarrierService implements ICarrierService {

    @Override
    public List<SuggestCarrierResponeDto> suggestCarrier(List<String> listRouteId) {
        SuggestCarrierResponeDto suggest = new SuggestCarrierResponeDto("routeId", "carrierId", 1.2f);
        // TODO Auto-generated method stub
        List<SuggestCarrierResponeDto> list = new ArrayList<>();
        list.add(suggest);
        return list;
    }

    @Override
    public String createShippingRequest(List<String> listIdRoute) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<CarrierRequestDto> getListRequestByFilter(CarrierRequestFilterDto filter) {
        // TODO Auto-generated method stub
        return null;
    }
    
    
}
