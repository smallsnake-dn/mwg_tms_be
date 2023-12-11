package com.mwg.tms.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
// @AllArgsConstructor
public class VehicleInfo {
    String licenseplate;

    public VehicleInfo(String licenseplate) {
        this.licenseplate = licenseplate;
    }
}
