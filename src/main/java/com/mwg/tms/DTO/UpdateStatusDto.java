package com.mwg.tms.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateStatusDto {
    Integer requestid;
    Boolean type;
    List<DriverInfo> driverinfo;
    VehicleInfo vehicleinfo;
}
