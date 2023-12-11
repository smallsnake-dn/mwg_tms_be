package com.mwg.tms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mwg.tms.entities.Driver;

public interface IDriverRepository extends JpaRepository<Driver, String>{
    @Query(value = "select d from Driver d where d.citizenidentificationcard = :citizenidentificationcard")
    Driver findBycitizenIdentificationCard(@Param("citizenidentificationcard") String citizenidentificationcard);
}
