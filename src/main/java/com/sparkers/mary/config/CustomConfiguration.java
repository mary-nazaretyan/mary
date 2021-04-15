package com.sparkers.mary.config;

import java.util.Date;
import java.util.Locale;

import com.sparkers.mary.model.entity.Partner;
import com.sparkers.mary.repository.PartnerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomConfiguration {

    @Bean
    public CommandLineRunner commandLineRunner(PartnerRepository partnerRepository) {


        return args -> {

            partnerRepository.deleteAll();

            for (int i = 0; i < 10; i++) {
                Partner partner = new Partner();

                partner.setRef("ref" + i);
                partner.setCompanyName("Company name" + i);
                partner.setExpires(new Date());//TODO change to LocalDate
                partner.setLocale(Locale.CANADA);
                partnerRepository.save(partner);
            }
        };


    }
}
