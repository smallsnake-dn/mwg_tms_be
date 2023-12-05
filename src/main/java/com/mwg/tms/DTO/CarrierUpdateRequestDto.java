package com.mwg.tms.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CarrierUpdateRequestDto {
    String routeId;
    String carrierId;
    String reason;
}
