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
    Route getRouteById(int id);
}
