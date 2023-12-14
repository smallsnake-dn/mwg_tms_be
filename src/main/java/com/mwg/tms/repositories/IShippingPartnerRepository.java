package com.mwg.tms.repositories;

import java.util.List;

import com.mwg.tms.entities.PhysicalLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mwg.tms.entities.ShippingPartner;
import com.mwg.tms.entities.TypeOfVehicle;

public interface IShippingPartnerRepository extends JpaRepository<ShippingPartner, String>{
    @Query(value = "select s from ShippingPartner s " +
            "left join TransportationResource t on s.shippingpartnerid = t.shippingpartnerid.shippingpartnerid " +
            "where t.locationid = :locationid and t.typeofvehicleid = :typeofvehicleid")
    List<ShippingPartner> getShippingPartnerByLocation(@Param("locationid") PhysicalLocation locationid,
    @Param("typeofvehicleid") TypeOfVehicle typeofvehicleid);

}
