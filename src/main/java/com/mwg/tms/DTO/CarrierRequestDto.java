package com.mwg.tms.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CarrierRequestDto {
    String requestId;
    String startLocation;
    String endLocation;
    Date startTime;
    String typeVehicle;
}
