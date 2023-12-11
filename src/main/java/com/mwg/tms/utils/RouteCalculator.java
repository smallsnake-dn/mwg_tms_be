package com.mwg.tms.utils;

import com.mwg.tms.entities.Route;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RouteCalculator {
    Route route;

    public RouteCalculator() {
    }
}
