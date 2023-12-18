package com.mwg.tms.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarrierUpdateRequestDto {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CarrierUpdate {
        String routeId;
        String carrierId;
        String reason;
    }

    CarrierUpdate data;
}
