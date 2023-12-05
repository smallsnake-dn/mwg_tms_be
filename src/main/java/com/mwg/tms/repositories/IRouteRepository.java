package com.mwg.tms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mwg.tms.entities.Route;

@Repository
public interface IRouteRepository extends JpaRepository<Route, Integer>{
    
}
