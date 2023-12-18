package com.mwg.tms.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CreateShippingRequestDto {
    List<String> data;
    public CreateShippingRequestDto(){}
}
