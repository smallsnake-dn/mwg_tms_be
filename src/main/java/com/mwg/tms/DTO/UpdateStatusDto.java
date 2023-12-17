package com.mwg.tms.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateStatusDto {
    String routeid;
    Boolean type;
    List<DriverInfo> driverinfo;
    String vehicleinfo;
}
