package com.mwg.tms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mwg.tms.entities.ShippingPartner;

public interface IShippingPartnerRepository extends JpaRepository<ShippingPartner, Integer>{
    
}
