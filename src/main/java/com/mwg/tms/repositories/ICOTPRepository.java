package com.mwg.tms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mwg.tms.entities.ChoiceOfTransportationPartner;

public interface ICOTPRepository extends JpaRepository<ChoiceOfTransportationPartner, String>{
    @Query(value = "select c from ChoiceOfTransportationPartner c " +
    "where c.carrentalinformationid = :id and c.deleteat IS NULL")
    ChoiceOfTransportationPartner findCOTP(@Param("id") String id);
    
    // @Query(value = "select c from ChoiceOfTransportationPartner where er id);
}
