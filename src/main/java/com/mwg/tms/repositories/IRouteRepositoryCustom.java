package com.mwg.tms.repositories;

import java.util.List;
import com.mwg.tms.DAO.IRoute;
import com.mwg.tms.DTO.RouteRequest;
import com.mwg.tms.DTO.RouteRespone;

public interface IRouteRepositoryCustom {
    List<RouteRespone> findAllBydeparturelocationid(RouteRequest routeRequest);
}
