package com.mwg.tms.controllers;

import com.mwg.tms.entities.Commune;
import com.mwg.tms.entities.Country;
import com.mwg.tms.entities.District;
import com.mwg.tms.entities.Province;
import com.mwg.tms.services.IProvincesService;
import com.mwg.tms.services.impl.ProvincesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProvincesController {
    private IProvincesService provincesService;
    public ProvincesController(ProvincesService provincesService) {
        this.provincesService = provincesService;
    }
    @GetMapping("/country")
    public ResponseEntity<List<Country>> getCountries() {
        List<Country> list = provincesService.getCountries();
        return ResponseEntity.ok().body(list);
    }
    @GetMapping("/province/{id}")
    public ResponseEntity<List<Province>> getProvinces(@PathVariable Integer id) {
        List<Province> list = provincesService.getProvinces(id);
        return ResponseEntity.ok().body(list);
    }
    @GetMapping("/district/{id}")
    public ResponseEntity<List<District>> getDistricts(@PathVariable Integer id) {
        List<District> list = provincesService.getDistricts(id);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/commune/{id}")
    public ResponseEntity<List<Commune>> getCommunes(@PathVariable Integer id) {
        List<Commune> list = provincesService.getCommunes(id);
        return ResponseEntity.ok().body(list);
    }


}
