package com.mwg.tms.DTO;

import com.mwg.tms.entities.CarRentalInfomation;
import com.mwg.tms.entities.ChoiceOfTransportationPartner;
import com.mwg.tms.entities.DeliveryPoint;
import com.mwg.tms.entities.Route;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteDetailDto {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private class RouteInfo {
        String routeid;
        String startLocation;
        String startTime;
        String endTime;
        String typeofvehicle;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private class DeliveryPointInfo {
        int ordinal;
        String deliverypointid;
        String deliverypointlocation;
        String arrivaltime;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private class Rental {
        Integer ordinal;
        String carrentalid;
        String shippingparner;
        String senderinformation;
        String approverinformation;
        String approvaldate;
        Integer status;
    }

    RouteInfo route;
    List<DeliveryPointInfo> deliverypoints;
    List<Rental> carrentals;

    public RouteDetailDto(Route route) {
        RouteInfo routeInfo = new RouteInfo();
        routeInfo.setRouteid(route.getRouteid());
        routeInfo.setStartLocation(route.getDeparturelocation().getExactaddress());
        routeInfo.setStartTime(route.getStarttime().toString());
        routeInfo.setEndTime(route.getEndtime().toString());
        routeInfo.setTypeofvehicle(route.getTypeofvehicle().getTypeofvehiclename());

        this.setRoute(routeInfo);

        this.deliverypoints = new ArrayList<>();
        for (DeliveryPoint d : route.getListdeliverypoint()) {
            DeliveryPointInfo deliveryPointInfo = new DeliveryPointInfo();
            deliveryPointInfo.setOrdinal(d.getOrdinalnumber() != null ? d.getOrdinalnumber() : null);
            deliveryPointInfo.setArrivaltime(d.getArrivaltime() != null ? d.getArrivaltime().toString() : null);
            deliveryPointInfo.setDeliverypointid(d.getDeliverypointid() != null ? d.getDeliverypointid() : null);
            deliveryPointInfo.setDeliverypointlocation(d.getDeliverypointlocation() != null ? d.getDeliverypointlocation() : null);

            this.deliverypoints.add(deliveryPointInfo);
        }

        this.carrentals = new ArrayList<>();
        for (CarRentalInfomation c : route.getListCarRentalInfomations()) {
            Rental rental = new Rental();
            rental.setCarrentalid(c.getCarrentalinformationid() != null ? c.getCarrentalinformationid() : null);
            rental.setApproverinformation(c.getApproverinformation() != null ? c.getApproverinformation() : null);
            rental.setApprovaldate(c.getApprovaldate() != null ? c.getApprovaldate().toString() : null);
            rental.setSenderinformation(c.getCreator() != null ? c.getCreator() : null);
            for (ChoiceOfTransportationPartner ch : c.getListcotp()) {
                if (ch.getDeleteat() == null) {
                    rental.setShippingparner(ch.getShippingPartner().getShippingparnername());
                    break;
                }
            }
            rental.setStatus(c.getStatus());

            this.carrentals.add(rental);
        }


    }
}
