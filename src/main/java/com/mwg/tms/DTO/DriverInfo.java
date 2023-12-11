package com.mwg.tms.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DriverInfo {
    String citizenIdentificationCard;
    String name;
    String phoneNumber;
}
