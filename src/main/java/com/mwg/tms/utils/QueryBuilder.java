package com.mwg.tms.utils;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.mwg.tms.DTO.Location;

public class QueryBuilder {
    String select;
    List<String> stringBuilder;

    public static QueryBuilder create() {
        return new QueryBuilder();
    }

    public QueryBuilder() {
        this.stringBuilder = new LinkedList<>();
    }

    public QueryBuilder Select(String select) {
        // this.stringBuilder.add(select);
        this.select = select;
        return this;
    }

    // public QueryBuilder And() {
    //     this.stringBuilder.add(" and ");
    //     return this;
    // }

    public QueryBuilder isNotNull(String data, boolean is) {
        this.stringBuilder.add(" " + data + " IS NOT NULL");
        return this;

    }

    public QueryBuilder startTime(Date date) {
        if (date != null) {
            String _date = FormatDate.format(date);
            this.stringBuilder.add(" starttime >= cast('" + _date + "' as instant) ");
        }
        return this;
    }

    public QueryBuilder endTime(Date date) {
        if (date != null) {
            String _date = FormatDate.format(date);
//            this.stringBuilder.add(" endtime <= cast('" + _date + "' as instant) ");
            this.stringBuilder.add(" starttime <= cast('" + _date + "' as instant) ");
        }
        return this;
    }

    /**
     * @param location
     */
    public QueryBuilder startLocation(Location location) {
        if(location == null) {
            return this;
        }
        List<String> listStr = new LinkedList<>();
        if (location.getCountryId() != -1) {
            listStr.add("stl.countryid.id = " + location.getCountryId());
        }
        if (location.getProvinceId() != -1) {
            listStr.add("stl.provinceid.id = " + location.getProvinceId());
        }
        if (location.getDistricId() != -1) {
            listStr.add("stl.districtid.id = " + location.getDistricId());
        }
        if (location.getCommuneId() != -1) {
            listStr.add("stl.communeid.id = " + location.getCommuneId());
        }
        if (!listStr.isEmpty()) {
            String data = String.join(" and ", listStr);
            this.stringBuilder.add(data);
        }
        return this;
    }

    public QueryBuilder endLocation(Location location) {
        if(location == null) {
            return this;
        }
        List<String> listStr = new LinkedList<>();
        if (location.getCountryId() != -1) {
            listStr.add("el.countryid.id = " + location.getCountryId());
        }
        if (location.getProvinceId() != -1) {
            listStr.add("el.provinceid.id = " + location.getProvinceId());
        }
        if (location.getDistricId() != -1) {
            listStr.add("el.districtid.id = " + location.getDistricId());
        }
        if (location.getCommuneId() != -1) {
            listStr.add("el.communeid.id = " + location.getCommuneId());
        }
        if (!listStr.isEmpty()) {
            String data = String.join(" and ", listStr);
            this.stringBuilder.add(data);
        }
        return this;
    }

    public String build() {
        return this.select + String.join(" and ", stringBuilder);
    }

}
