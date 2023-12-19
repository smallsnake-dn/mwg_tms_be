package com.mwg.tms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mwg.tms.entities.Route;

@Repository
public interface IRouteRepository extends JpaRepository<Route, String> {
    @Query(value = "select r from Route r where r.routeid = :id")
    Route getRouteById(@Param("id") String id);

    @Query(value = "select COUNT(*) from Route r left join CarRentalInfomation c on r.routeid = c.routeid " +
            "left join ChoiceOfTransportationPartner ch on c.carrentalinformationid = ch.carrentalinformationid " +
            "where ch.shippingPartner.shippingpartnerid = :shippingpartnerid  " +
            "and (c.status = 0 or c.status = 1) " +
            "and r.typeofvehicle.typeofvehicelid = :typeofvehicleid " +
            " and r.departurelocation.locationid = :departurelocation and ch.deleteat IS NULL")
    Integer getNumberOfVehicleBusy(@Param("shippingpartnerid") String shippingpartnerid,
            @Param("typeofvehicleid") String typeOfVehicle,
            @Param("departurelocation") String departurelocation);
}
