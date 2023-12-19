package com.mwg.tms.DTO;

import com.mwg.tms.DAO.RouteResponeDao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestResponeDto {
    String requestid;
    String routeid;
    String startlocation;
    Instant starttime;
    Instant endtime;
    String vehicletype;
    String vehiclenumber;
    String status;

    public static List<RequestResponeDto> castFromRouteResponeDao(List<RouteResponeDao> routesResponeDao) throws Exception {
        List<RequestResponeDto> requestResponeDto = new ArrayList<>();
        for (RouteResponeDao r : routesResponeDao) {
            RequestResponeDto rrd = new RequestResponeDto();
            rrd.setRequestid(r.getCarRentalInfomation() != null ? r.getCarRentalInfomation().getCarrentalinformationid() : null);
            rrd.setRouteid(r.getRouteId());
            rrd.setStartlocation(r.getDepartureLocationName());
            rrd.setStarttime(r.getStartTime());
            rrd.setEndtime(r.getEndTime());
            rrd.setVehicletype(r.getCarRentalInfomation().getVehicle() != null ? r.getCarRentalInfomation().getVehicle().getTypeofvehicleid().getTypeofvehiclename() : null);
            rrd.setVehiclenumber(r.getCarRentalInfomation().getVehicle() != null? r.getCarRentalInfomation().getVehicle().getLicenseplate() : null);
            rrd.setStatus(r.getCarRentalInfomation().getStatus().toString());

            requestResponeDto.add(rrd);
        }
        return requestResponeDto;
    }
}
