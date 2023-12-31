package com.mwg.tms.repositories.impl;

import java.util.List;

import com.mwg.tms.DAO.RouteResponeDao;
import org.springframework.stereotype.Repository;

import com.mwg.tms.DTO.RouteRequest;
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
    public List<RouteResponeDao> findAllBydeparturelocationid(RouteRequest routeRequest) {
        String query = QueryBuilder.create()
                .Select("select new com.mwg.tms.DAO.RouteResponeDao(r.id, stl.exactaddress as departureLocation, r.starttime," +
                        " el.exactaddress as endingLocation, r.endtime, c) from\r\n"
                        + //
                        "             Route r\r\n" + //
                        // " left join SuggestShippingUnit rs\r\n" + //
                        // " on r.routeid = rs.routeid.id\r\n" + //
                        "                 left join PhysicalLocation stl\r\n" + //
                        "                     on r.departurelocation.id = stl.id\r\n" + //
                        "                 left join PhysicalLocation el\r\n" + //
                        "                     on r.endinglocation.id = el.id\r\n" + //
                        "                 left join CarRentalInfomation c\r\n" + //
                        "                     on r.routeid = c.routeid\r\n" + //
                        "                 left join ChoiceOfTransportationPartner ch\r\n" + //
                        "                     on c.carrentalinformationid = ch.carrentalinformationid \r\n" + //
                        "         where ch.deleteat IS NULL and ")
                .startLocation(routeRequest.getData().getLocation())
                 .startTime(routeRequest.getData().getFromDate()).endTime(routeRequest.getData().getEndDate()).build();
//                .startTime(routeRequest.getData().getFromDate()).build();
//        System.out.println("communeIdddd:  " + FormatDate.format(routeRequest.getData().getFromDate()));
//        System.out.println(query);
        if (routeRequest.getData().getStatus() != null) {
            switch (routeRequest.getData().getStatus()) {
                case -2:
//                    query = query + "and c.status IS NULL";
                    query = query + " and c.carrentalinformationid IS NULL";

                    break;
                case -1:
                    query = query + "and c.status IS NULL and c.carrentalinformationid IS NOT NULL ";
                    break;
                case 0:
                    query = query + "and c.status = 0";
                    break;
                case 1:
                    query = query + "and c.status  = 1";
                    break;
                case 2:
                    query = query + "and c.status  = 2";
                    break;

                default:
                    System.out.println("Khong them duoc status vao query route");
                    break;
            }
        } else {
//            query = query + " and c.carrentalinformationid IS NULL";
        }
        List<RouteResponeDao> l = null;
        try {
            Query q = entityManager.createQuery(query, Route.class);
            l = q.getResultList();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }

        System.out.println("size: " + l.size());
        return l;
    }

}
