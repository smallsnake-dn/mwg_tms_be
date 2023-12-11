package com.mwg.tms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mwg.tms.entities.Vehicle;

public interface IVehicleRepository extends JpaRepository<Vehicle, Integer>{
    @Query(value = "select v from Vehicle v where v.licenseplate = :licenseplate")
    Vehicle findByLicensePlate(@Param("licenseplate") String licenseplate);
}
