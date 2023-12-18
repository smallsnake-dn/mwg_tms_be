package com.mwg.tms.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStatusDto {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateStatus {
        String routeid;
        Boolean type;
        List<DriverInfo> driverinfo;
        String vehicleinfo;
    }

    UpdateStatus data;

}
