package com.sparkers.mary.controller;

import java.util.List;

import com.sparkers.mary.model.Partner;
import com.sparkers.mary.service.PartnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
@RequiredArgsConstructor
public class PartnerController {

    private final PartnerService partnerService;

    @GetMapping("/api/partners")
    public List<Partner> getPartners() {
        return partnerService.getPartners();
    }

    @GetMapping("/api/partners/{id}")
    public Partner getPartnerById(@PathVariable("id") Long id) {
        return partnerService.getPartnerById(id);
    }

    @PostMapping("/api/partners")
    public void addPartner(Partner partner) {
        partnerService.addPartner(partner);
    }

    @PutMapping("/api/partners/{id}")
    public void updatePartnerById(@PathVariable("id") Long id, Partner partner) {
        partnerService.updatePartner(id, partner);
    }

    @DeleteMapping("/api/partners/{id}")
    public void deletePartnerById(@PathVariable("id") Long id) {
        partnerService.deleteById(id);
    }
}
