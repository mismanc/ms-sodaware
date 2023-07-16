package com.sodaware.web.services.inventory;

import com.sodaware.web.services.inventory.model.SodaInventoryDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@ConfigurationProperties(prefix = "soda.inventory")
@Service
@Profile("!local-discovery")
public class SodaInventoryRestTemplateImpl implements SodaInventoryService {

    @Value("soda.inventory.service-host")
    private String serviceHost;

    // @Value("soda.inventory.service-password")
    // private String servicePassword;

    public static final String INVENTORY_PATH = "/api/v1/soda/{sodaId}/inventory";
    private final RestTemplate restTemplate;

    private static String BASIC_AUTH;



    public void setServiceHost(String serviceHost) {
        this.serviceHost = serviceHost;
    }

    public SodaInventoryRestTemplateImpl(RestTemplate restTemplate, @Value("${soda.inventory.service-user}") String serviceUser, @Value("${soda.inventory.service-password}") String servicePassword) {
        this.restTemplate = restTemplate;
        BASIC_AUTH = "Basic " + Base64.getEncoder().encodeToString((serviceUser + ":" + servicePassword).getBytes());
    }

    @Override
    public Integer getOnHandInventory(UUID sodaId) {
        ResponseEntity<List<SodaInventoryDto>> responseEntity;
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", BASIC_AUTH);
            responseEntity = restTemplate.exchange(serviceHost + INVENTORY_PATH,
                    HttpMethod.GET,
                    new HttpEntity<>(headers),
                    new ParameterizedTypeReference<>() {
                    }, sodaId);
        } catch (Exception e) {
            return 0;
        }


        if (responseEntity.getStatusCodeValue() != 200) {
            return 0;
        }

        Integer totalOnHand = Objects.requireNonNull(responseEntity.getBody()).stream().mapToInt(SodaInventoryDto::getQuantityOnHand).sum();
        return totalOnHand;
    }
}
