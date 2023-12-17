package com.mwg.tms.services.impl;

import com.mwg.tms.entities.Commune;
import com.mwg.tms.entities.Country;
import com.mwg.tms.entities.District;
import com.mwg.tms.entities.Province;
import com.mwg.tms.repositories.ICommuneRepository;
import com.mwg.tms.repositories.ICountriesRepository;
import com.mwg.tms.repositories.IDistrictsRepository;
import com.mwg.tms.repositories.IProvincesRepository;
import com.mwg.tms.services.IProvincesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvincesService implements IProvincesService {
    private ICountriesRepository countriesRepository;
    private IProvincesRepository provincesRepository;
    private IDistrictsRepository districtsRepository;
    private ICommuneRepository communeRepository;

    public ProvincesService(ICountriesRepository countriesRepository, IProvincesRepository provincesRepository,
                            IDistrictsRepository districtsRepository, ICommuneRepository communeRepository) {
        this.countriesRepository = countriesRepository;
        this.provincesRepository = provincesRepository;
        this.districtsRepository = districtsRepository;
        this.communeRepository = communeRepository;
    }
    @Override
    public List<Country> getCountries() {
        return countriesRepository.findAll();
    }

    @Override
    public List<Province> getProvinces(Integer countryid) {
        return provincesRepository.getListProvince(countryid);
    }

    @Override
    public List<District> getDistricts(Integer provinceid) {
        return districtsRepository.getListDistrict(provinceid);
    }

    @Override
    public List<Commune> getCommunes(Integer districtid) {
        return communeRepository.getListCommune(districtid);
    }
}
