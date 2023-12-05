package com.mwg.tms.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PackageResponeDto {
    int STT;
    String packageId;
    String CCCD;
    String name;
    String phoneNumber;
    String type;
}
