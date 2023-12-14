package com.mwg.tms.utils;


import com.mwg.tms.entities.Route;
import com.mwg.tms.entities.ShippingPartner;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoutePrice {
    ShippingPartner shippingPartner;
    Route route;
    double price;
    public RoutePrice(){}
}
