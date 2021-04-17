package com.sparkers.mary;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.Locale;

import com.sparkers.mary.model.dto.PartnerDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class PartnerTest {

    @Test
    void shouldGetPartners(@Autowired TestRestTemplate restTemplate) {
        //given, when
        final ResponseEntity<PartnerDTO[]> responseEntity =
            restTemplate.getForEntity("/api/partners?from=0&size=5", PartnerDTO[].class);
        final PartnerDTO[] partners = responseEntity.getBody();

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(partners).hasSize(5);
    }

    @Test
    void shouldGetPartnerById(@Autowired TestRestTemplate restTemplate) {
        //given, when
        final ResponseEntity<PartnerDTO> responseEntity =
            restTemplate.getForEntity("/api/partners/1", PartnerDTO.class);
        final PartnerDTO partner = responseEntity.getBody();

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(partner).isNotNull();
        assertThat(partner.getId()).isEqualTo(1);
    }

    @Test
    void shouldNotGetPartnerById(@Autowired TestRestTemplate restTemplate) {
        //given, when
        final ResponseEntity<PartnerDTO> responseEntity =
            restTemplate.getForEntity("/api/partners/13", PartnerDTO.class);
        final PartnerDTO partner = responseEntity.getBody();

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(partner).isNotNull();
        assertThat(partner.getId()).isNull();
    }

    @Test
    void shouldAddPartner(@Autowired TestRestTemplate restTemplate) {
        //given
        final PartnerDTO partnerDTO = new PartnerDTO();
        partnerDTO.setReference("ref11");
        partnerDTO.setName("Partner name11");
        partnerDTO.setExpirationTime(new Date());
        partnerDTO.setLocale(Locale.CANADA);

        //when
        final ResponseEntity<PartnerDTO> responseEntity =
            restTemplate.postForEntity("/api/partners", partnerDTO, PartnerDTO.class);
        final PartnerDTO partner = responseEntity.getBody();

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(partner).isNotNull();
        assertThat(partner.getId()).isEqualTo(11);
    }

    @Test
    void shouldUpdatePartner(@Autowired TestRestTemplate restTemplate) {
        //given
        final PartnerDTO partnerDTO = new PartnerDTO();
        partnerDTO.setReference("ref4");
        partnerDTO.setName("Partner name4 updated");
        partnerDTO.setExpirationTime(new Date());
        partnerDTO.setLocale(Locale.FRANCE);

        HttpEntity<PartnerDTO> request = new HttpEntity<>(partnerDTO, new HttpHeaders());

        //when
        final ResponseEntity<PartnerDTO> responseEntity =
            restTemplate.exchange("/api/partners/4", HttpMethod.PUT, request, PartnerDTO.class);
        final PartnerDTO partner = responseEntity.getBody();

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(partner).isNotNull();
        assertThat(partner.getId()).isEqualTo(4);
        assertThat(partner.getName()).isEqualTo("Partner name4 updated");
    }

    @Test
    void shouldNotUpdatePartner(@Autowired TestRestTemplate restTemplate) {
        //given
        final PartnerDTO partnerDTO = new PartnerDTO();
        partnerDTO.setReference("ref18");
        partnerDTO.setName("Partner name18");
        partnerDTO.setExpirationTime(new Date());
        partnerDTO.setLocale(Locale.FRANCE);

        HttpEntity<PartnerDTO> request = new HttpEntity<>(partnerDTO, new HttpHeaders());

        //when
        final ResponseEntity<PartnerDTO> responseEntity =
            restTemplate.exchange("/api/partners/18", HttpMethod.PUT, request, PartnerDTO.class);
        final PartnerDTO partner = responseEntity.getBody();

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(partner).isNotNull();
        assertThat(partner.getId()).isNull();
    }

    @Test
    void shouldDeleteById(@Autowired TestRestTemplate restTemplate) {
        //when
        HttpEntity<PartnerDTO> request = new HttpEntity<>(new PartnerDTO(), new HttpHeaders());
        final ResponseEntity<PartnerDTO> responseEntity =
            restTemplate.exchange("/api/partners/3", HttpMethod.DELETE, request, PartnerDTO.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
