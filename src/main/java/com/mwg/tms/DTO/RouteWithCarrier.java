package com.mwg.tms.DTO;

import java.util.HashMap;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RouteWithCarrier {
    String routeid;
    HashMap<String, Float> carriers;
    public RouteWithCarrier(String routeid) {
        this.routeid = routeid;
        this.carriers = new HashMap<>();
    }
}
