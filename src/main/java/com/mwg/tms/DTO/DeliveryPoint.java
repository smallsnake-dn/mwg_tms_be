package com.mwg.tms.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeliveryPoint {
    int STT;
    String deliveryPointId;
    String deliveryPointLocation;
    Date arrivalTime;
}
