package com.mwg.tms.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SuggestRequestDto {
//    @Data
//    @AllArgsConstructor
//    public static class ListRouteId {
//        List<String> listIdRoute;
//    }

//    ListRouteId data;
    List<String> data;
    public SuggestRequestDto() {}
//    public SuggestRequestDto(ListRouteId data) {
//        this.data = data;
//    }
}
