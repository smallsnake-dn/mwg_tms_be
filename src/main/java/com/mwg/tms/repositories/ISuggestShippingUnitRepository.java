package com.mwg.tms.repositories;

import com.mwg.tms.entities.SuggestShippingUnit;
import com.mwg.tms.entities.SuggestShippingUnitId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISuggestShippingUnitRepository extends JpaRepository<SuggestShippingUnit, SuggestShippingUnitId> {
}
