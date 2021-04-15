package com.sparkers.mary.service;

import static java.util.stream.Collectors.toUnmodifiableList;

import java.util.List;

import com.sparkers.mary.model.dto.PartnerDTO;
import com.sparkers.mary.model.entity.Partner;
import com.sparkers.mary.repository.PartnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PartnerService {

    private final PartnerRepository partnerRepository;

    public List<PartnerDTO> getPartners(Pageable pageable) {
        return partnerRepository.findAll(pageable)
            .stream()
            .map(PartnerDTO::new)
            .collect(toUnmodifiableList());
    }

    public PartnerDTO getPartnerById(Long id) {
        return new PartnerDTO(partnerRepository.getOne(id));
    }

    public void addPartner(PartnerDTO partnerDTO) {
        final Partner partner = new Partner();
        partner.setId(partnerDTO.getId());
        partner.setCompanyName(partnerDTO.getName());
        partner.setRef(partnerDTO.getReference());
        partner.setLocale(partnerDTO.getLocale());
        partner.setExpires(partnerDTO.getExpirationTime());

        partnerRepository.save(partner);
    }

    public void updatePartner(PartnerDTO partnerDTO) {
        partnerRepository.findById(partnerDTO.getId())
            .ifPresent(partner -> {
                partner.setLocale(partnerDTO.getLocale());
                partner.setCompanyName(partnerDTO.getName());
                partner.setRef(partnerDTO.getReference());
                partner.setExpires(partnerDTO.getExpirationTime());
            });
    }

    public void deleteById(Long id) {
        partnerRepository.deleteById(id);
    }
}