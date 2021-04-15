package com.sparkers.mary.controller;

import java.util.List;

import com.sparkers.mary.model.dto.PartnerDTO;
import com.sparkers.mary.repository.OffsetBasedPageRequest;
import com.sparkers.mary.service.PartnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/partners")
public class PartnerController {

    private final PartnerService partnerService;

    @GetMapping()
    public List<PartnerDTO> getPartners(@RequestParam(name = "from", defaultValue = "0") int from,
                                        @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = new OffsetBasedPageRequest(from, size);
        return partnerService.getPartners(pageable);
    }

    @GetMapping("/{id}")
    public PartnerDTO getPartnerById(@PathVariable("id") Long id) {
        return partnerService.getPartnerById(id);
    }

    @PostMapping()
    public void addPartner(@RequestBody PartnerDTO partner) {
        partnerService.addPartner(partner);
    }

    @PutMapping("/{id}")
    public void updatePartnerById(@PathVariable("id") Long id, @RequestBody PartnerDTO partner) {
        partner.setId(id);
        partnerService.updatePartner(partner);
    }

    @DeleteMapping("/{id}")
    public void deletePartnerById(@PathVariable("id") Long id) {
        partnerService.deleteById(id);
    }
}
