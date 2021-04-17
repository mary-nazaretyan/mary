package com.sparkers.mary.controller;

import java.util.List;

import com.sparkers.mary.model.dto.PartnerDTO;
import com.sparkers.mary.repository.OffsetBasedPageRequest;
import com.sparkers.mary.service.PartnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * A single REST API for {@link PartnerDTO} entity.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/partners")
public class PartnerController {

    private final PartnerService partnerService;

    /**
     * Returns the list of {@link PartnerDTO} entities for a given interval.
     *
     * @param from Offset in the result set to paginate to, default value is 0.
     * @param size Window pagination size, default value is 10.
     *
     * @return the list of {@link PartnerDTO} entities and response status.
     */
    @GetMapping()
    public ResponseEntity<List<PartnerDTO>> getPartners(@RequestParam(name = "from", defaultValue = "0") int from,
                                                        @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = new OffsetBasedPageRequest(from, size, Sort.by("id"));
        return new ResponseEntity<>(partnerService.getPartners(pageable), HttpStatus.OK);
    }

    /**
     * Returns the {@link PartnerDTO} entity with specified id.
     *
     * @param id id of the partnerDTO.
     *
     * @return {@link PartnerDTO} entity and response status.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PartnerDTO> getPartnerById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(partnerService.getPartnerById(id), HttpStatus.OK);
    }

    /**
     * Adds a partner to db and returns the {@link PartnerDTO} entity.
     *
     * @param partner {@link PartnerDTO} entity to be added.
     *
     * @return added {@link PartnerDTO} entity and response status.
     */
    @PostMapping()
    public ResponseEntity<PartnerDTO> addPartner(@RequestBody @Validated PartnerDTO partner) {
        final PartnerDTO partnerDTO = partnerService.addPartner(partner);
        return new ResponseEntity<>(partnerDTO, HttpStatus.CREATED);
    }

    /**
     * Updates the already existing partner entity with the provided id and returns it. Otherwise returns the error
     * response status and message.
     *
     * @param id      id of partner to be updated.
     * @param partner {@link PartnerDTO} entity for updating
     *
     * @return already updated {@link PartnerDTO} entity
     */
    @PutMapping("/{id}")
    public ResponseEntity<PartnerDTO> updatePartnerById(@PathVariable("id") Long id,
                                                        @RequestBody @Validated PartnerDTO partner) {
        partner.setId(id);
        final PartnerDTO partnerDTO = partnerService.updatePartner(partner);
        return new ResponseEntity<>(partnerDTO, HttpStatus.OK);
    }

    /**
     * Deletes the {@link PartnerDTO} entity with the specified id or returns error message and status.
     *
     * @param id partner id
     *
     * @return response status
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePartnerById(@PathVariable("id") Long id) {
        partnerService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
