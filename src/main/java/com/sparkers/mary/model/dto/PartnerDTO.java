package com.sparkers.mary.model.dto;

import java.util.Date;
import java.util.Locale;

import javax.validation.constraints.NotEmpty;

import com.sparkers.mary.model.entity.Partner;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PartnerDTO {

    public PartnerDTO(Partner partner) {
        this.id = partner.getId();
        this.name = partner.getCompanyName();
        this.reference = partner.getRef();
        this.locale = partner.getLocale();
        this.expirationTime = partner.getExpires();
    }

    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String reference;
    private Locale locale;
    private Date expirationTime;
}
