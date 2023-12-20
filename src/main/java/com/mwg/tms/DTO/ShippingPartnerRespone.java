package com.mwg.tms.DTO;

import com.mwg.tms.entities.ShippingPartner;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShippingPartnerRespone {
    String shippingPartnerId;
    String shippingPartnerName;

    public static List<ShippingPartnerRespone> castFromShippingPartner(List<ShippingPartner> list) {
        List<ShippingPartnerRespone> shippingPartnerResponeList = new ArrayList<>();
        for(ShippingPartner s : list) {
            ShippingPartnerRespone shippingPartnerRespone = new ShippingPartnerRespone();
            shippingPartnerRespone.setShippingPartnerId(s.getShippingpartnerid());
            shippingPartnerRespone.setShippingPartnerName(s.getShippingparnername());
            shippingPartnerResponeList.add(shippingPartnerRespone);
        }
        return shippingPartnerResponeList;
    }
}
