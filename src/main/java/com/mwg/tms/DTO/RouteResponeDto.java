package com.mwg.tms.DTO;

import java.util.ArrayList;
import java.util.Date;

import com.mwg.tms.DAO.RouteResponeDao;
import com.mwg.tms.entities.ChoiceOfTransportationPartner;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteResponeDto {
    String routeId;
    String departureLocationName;
    Instant startTime;
    String endingLocationName;
    Instant endTime;
    String status;
    String carrier;
    String licenseplate;

    public static List<RouteResponeDto> castFromRouteResponeDao(List<RouteResponeDao> routeResponeDaoList) throws Exception{
        List<RouteResponeDto> listRouteResponeDto = new ArrayList<>();
        for (RouteResponeDao r : routeResponeDaoList) {
            RouteResponeDto routeResponeDto = new RouteResponeDto();

            routeResponeDto.setRouteId(r.getRouteId() != null ? r.getRouteId() : null);
            routeResponeDto.setDepartureLocationName(r.getDepartureLocationName() != null ? r.getDepartureLocationName() : null);
            routeResponeDto.setStartTime(r.getStartTime() != null ? r.getStartTime() : null);
            routeResponeDto.setEndingLocationName(r.getEndingLocationName() != null ? r.getEndingLocationName() : null);
            routeResponeDto.setEndTime(r.getEndTime() != null ? r.getEndTime() : null);
//             chua xy ly status
            if (r.getCarRentalInfomation() != null) {
                routeResponeDto.setStatus(r.getCarRentalInfomation().getStatus() != null ? r.getCarRentalInfomation().getStatus().toString() : null);
                for (ChoiceOfTransportationPartner ch : r.getCarRentalInfomation().getListcotp()) {
                    if (ch.getDeleteat() == null) {
                        routeResponeDto.setCarrier(ch.getShippingPartner().getShippingparnername() != null ?
                                ch.getShippingPartner().getShippingparnername() : null);
                        break;
                    }
                }
                routeResponeDto.setLicenseplate(r.getCarRentalInfomation().getVehicle() != null ?
                        r.getCarRentalInfomation().getVehicle().getLicenseplate() : null);
            }
            listRouteResponeDto.add(routeResponeDto);
        }
        return listRouteResponeDto;
    }
}
