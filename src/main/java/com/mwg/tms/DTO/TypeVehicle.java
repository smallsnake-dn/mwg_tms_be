package com.mwg.tms.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TypeVehicle {
    String vehicleClass;
    float weight;
    float volume;
    String feature;
}
