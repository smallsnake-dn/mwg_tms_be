package com.mwg.tms.repositories;

import com.mwg.tms.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICountriesRepository extends JpaRepository<Country, Integer> {
}
