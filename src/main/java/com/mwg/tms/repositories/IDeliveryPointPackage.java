package com.mwg.tms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mwg.tms.entities.DeliveryPointPackage;
import com.mwg.tms.entities.DeliveryPointPackageId;

@Repository
public interface IDeliveryPointPackage extends JpaRepository<DeliveryPointPackage, DeliveryPointPackageId>{
    @Query(value = "select d from DeliveryPointPackage d where d.id.deliverypointid = :id")
    List<DeliveryPointPackage> findBydeliverypointid(@Param("id") String id);   
}
