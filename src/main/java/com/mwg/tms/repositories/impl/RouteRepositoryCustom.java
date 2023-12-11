package com.mwg.tms.repositories.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mwg.tms.DAO.IRoute;
import com.mwg.tms.DTO.RouteRequest;
import com.mwg.tms.DTO.RouteRespone;
import com.mwg.tms.entities.Route;
import com.mwg.tms.repositories.IRouteRepositoryCustom;
import com.mwg.tms.utils.FormatDate;
import com.mwg.tms.utils.QueryBuilder;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class RouteRepositoryCustom implements IRouteRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<RouteRespone> findAllBydeparturelocationid(RouteRequest routeRequest) {
        String query = QueryBuilder.create()
                .Select("select new com.mwg.tms.DTO.RouteRespone(r.id, stl.exactaddress as departureLocation, r.starttime, el.exactaddress as endingLocation, r.endtime) from\r\n" + //
                        "             Route r\r\n" + //
                        "                 left join SuggestShippingUnit rs\r\n" + //
                        "                     on r.id = rs.routeid.id\r\n" + //
                        "                 left join PhysicalLocation stl\r\n" + //
                        "                     on r.departurelocation.id = stl.id\r\n" + //
                        "                 left join PhysicalLocation el\r\n" + //
                        "                     on r.endinglocation.id = el.id\r\n" + //
                        "         where ")
                .startLocation(routeRequest.getLocation())
                // .startTime(routeRequest.getFromDate()).endTime(routeRequest.getEndDate()).build();
                .startTime(routeRequest.getFromDate()).build();
        System.out.println("communeIdddd:  " + FormatDate.format(routeRequest.getFromDate()));
        System.out.println(query);
        List<RouteRespone> l = null;
        try {
            Query q = entityManager.createQuery(query, Route.class);
            l = (List<RouteRespone>) q.getResultList();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
        System.out.println("size: " + l.size());
        return l;
    }

}
