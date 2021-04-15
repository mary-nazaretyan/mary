package com.sparkers.mary.service;

import java.util.List;

import com.sparkers.mary.model.Partner;
import com.sparkers.mary.repository.PartnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PartnerService {

    private final PartnerRepository partnerRepository;

    public List<Partner> getPartners() {
        return partnerRepository.findAll();
    }

    public Partner getPartnerById(Long id) {
        return partnerRepository.getOne(id);
    }

    public void addPartner(Partner partner) {
        partnerRepository.save(partner);
    }

    public void updatePartner(Long id, Partner partner) {
        partnerRepository.deleteById(id);
        partner.setId(id);
        partnerRepository.save(partner);
    }

    public void deleteById(Long id) {
        partnerRepository.deleteById(id);
    }
}
