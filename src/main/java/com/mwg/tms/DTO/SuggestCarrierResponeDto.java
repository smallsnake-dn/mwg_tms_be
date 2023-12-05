package com.mwg.tms.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SuggestCarrierResponeDto {
    String routeId;
    String carrierId;
    float cost;
}
