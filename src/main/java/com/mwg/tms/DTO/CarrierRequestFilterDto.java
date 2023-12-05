package com.mwg.tms.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CarrierRequestFilterDto {
    Date startDate;
    Date endDate;
}
