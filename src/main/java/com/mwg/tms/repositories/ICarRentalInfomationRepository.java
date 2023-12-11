package com.mwg.tms.repositories;

import java.time.Instant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.mwg.tms.entities.CarRentalInfomation;
import com.mwg.tms.entities.Route;

public interface ICarRentalInfomationRepository extends JpaRepository<CarRentalInfomation, Integer> {
        @Query(value = "update CarRentalInfomation c set c.timecreate = :timecreate, " +
                        "c.approverinformation = :approverinformation, c.approvaldate = :approvaldate, " +
                        "c.status = :status where c.routeid.id = :route and c.status IS NULL")
        void UpdateCarRentalInformation(@Param("timecreate") Instant timecreate,
                        @Param("approverinformation") String approverinformation,
                        @Param("approvaldate") Instant approvaldate,
                        @Param("status") Integer status,
                        @Param("route") Integer route);


        @Transactional
        @Modifying
        @Query(value = "update CarRentalInfomation c set c.timecreate = :timecreate, " +
                        "c.status = :status where c.routeid.id = :route and c.status IS NULL")
        void CreateCarRentalInformation(@Param("timecreate") Instant timecreate,
                        @Param("status") Integer status,
                        @Param("route") Integer route);

        @Query(value = "select c from CarRentalInfomation c where c.routeid.id = :id and c.status IS NULL")
        CarRentalInfomation findByRouteid(@Param("id") Integer id);
}
