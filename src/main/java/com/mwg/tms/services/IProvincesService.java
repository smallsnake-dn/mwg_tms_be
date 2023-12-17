package com.mwg.tms.services;

import com.mwg.tms.entities.Commune;
import com.mwg.tms.entities.Country;
import com.mwg.tms.entities.District;
import com.mwg.tms.entities.Province;

import java.util.List;

public interface IProvincesService {
    public List<Country> getCountries();
    public List<Province> getProvinces(Integer countryid);
    public List<District> getDistricts(Integer provinceid);
    public List<Commune> getCommunes(Integer districtid);
}
