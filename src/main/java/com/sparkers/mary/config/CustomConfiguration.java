package com.sparkers.mary.config;

import java.util.Date;
import java.util.Locale;
import java.util.stream.IntStream;

import com.sparkers.mary.model.entity.Partner;
import com.sparkers.mary.repository.PartnerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomConfiguration {

    /**
     * Loads 10 entries to the "partner" table.
     *
     * @param partnerRepository Partner repository.
     *
     * @return {@link CommandLineRunner} instance.
     */
    @Bean
    public CommandLineRunner commandLineRunner(PartnerRepository partnerRepository) {

        return args -> {
            partnerRepository.deleteAll();

            IntStream.range(1, 11)
                .forEach(i -> {
                    Partner partner = new Partner();
                    partner.setRef("Ref" + i);
                    partner.setCompanyName("Partner Name" + i);
                    partner.setExpires(new Date());
                    partner.setLocale(Locale.CANADA);
                    partnerRepository.save(partner);
                });
        };
    }
}
