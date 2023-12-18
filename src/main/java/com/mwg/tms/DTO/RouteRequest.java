package com.mwg.tms.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class RouteRequest {
    @Data
    @AllArgsConstructor
    public static class GetListData {
        Date fromDate;
        Date endDate;
        Location location;
        Integer status;
    }

    GetListData data;
}
