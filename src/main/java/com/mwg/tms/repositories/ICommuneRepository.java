package com.mwg.tms.repositories;

import com.mwg.tms.entities.Commune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICommuneRepository extends JpaRepository<Commune, Integer> {
    @Query(value = "select c from Commune c where c.districtid.id =:id")
    List<Commune> getListCommune(@Param("id") Integer id);
}
