package com.mwg.tms.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateCarrierRequestDto {
    String requestId;
    int type;
    List<DriverInfo> drivers;
    String vehicleId;
}
