package com.sodaware.web.services.inventory;

import com.sodaware.web.services.inventory.model.SodaInventoryDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@ConfigurationProperties(prefix = "soda.inventory", ignoreUnknownFields = false)
@Service
@Profile("!local-discovery")
public class SodaInventoryRestTemplateImpl implements SodaInventoryService {

    public static final String INVENTORY_PATH = "/api/v1/soda/{sodaId}/inventory";
    private final RestTemplate restTemplate;

    private String serviceHost;

    public void setServiceHost(String serviceHost) {
        this.serviceHost = serviceHost;
    }

    public SodaInventoryRestTemplateImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Integer getOnHandInventory(UUID sodaId) {
        ResponseEntity<List<SodaInventoryDto>> responseEntity;
        try {
            responseEntity = restTemplate.exchange(serviceHost + INVENTORY_PATH,
                    HttpMethod.GET,
                    null,
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
