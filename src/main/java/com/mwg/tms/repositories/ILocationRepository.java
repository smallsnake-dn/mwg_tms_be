package com.mwg.tms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mwg.tms.entities.PhysicalLocation;

public interface ILocationRepository extends JpaRepository<PhysicalLocation, Integer>{
    
}
