package com.mwg.tms.repositories;

import com.mwg.tms.entities.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IDistrictsRepository extends JpaRepository<District, Integer> {
    @Query(value = "select d from District d where d.provinceid.id = :id")
    List<District> getListDistrict(@Param("id") Integer id);
}
