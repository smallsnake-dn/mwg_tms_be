package com.mwg.tms.repositories;

import java.util.List;
import com.mwg.tms.DAO.RouteResponeDao;
import com.mwg.tms.DTO.RouteRequest;

public interface IRouteRepositoryCustom {
    List<RouteResponeDao> findAllBydeparturelocationid(RouteRequest routeRequest);
}
