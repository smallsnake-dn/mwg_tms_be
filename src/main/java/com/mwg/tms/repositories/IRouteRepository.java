package com.mwg.tms.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mwg.tms.DAO.IRoute;
import com.mwg.tms.entities.Route;

@Repository
public interface IRouteRepository extends JpaRepository<Route, Integer>{
    @Query(value = "select r from Route r where r.id = :id")
    Route getRouteById(@Param("id") Integer id);
}
