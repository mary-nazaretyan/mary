package com.sparkers.mary.repository;

import com.sparkers.mary.model.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnerRepository extends JpaRepository<Partner, Long> {
}
