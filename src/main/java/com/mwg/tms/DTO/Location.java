package com.mwg.tms.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Location {
    int countryId;
    int provinceId;
    int districId;
    int communeId;
    public Location() {
        this.countryId = -1;
        this.provinceId = -1;
        this.districId = -1;
        this.communeId = -1;
    }
}
