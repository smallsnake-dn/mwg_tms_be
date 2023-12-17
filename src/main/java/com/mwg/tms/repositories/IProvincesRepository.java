package com.mwg.tms.repositories;

import com.mwg.tms.entities.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProvincesRepository extends JpaRepository<Province, Integer> {
    @Query(value = "select p from Province p where p.countryid.id = :id")
    List<Province> getListProvince(@Param("id") Integer id);
}
