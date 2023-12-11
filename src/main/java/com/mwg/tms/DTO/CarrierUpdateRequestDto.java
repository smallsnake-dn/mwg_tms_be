package com.mwg.tms.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CarrierUpdateRequestDto {
    Integer routeId;
    Integer carrierId;
    String reason;
}
