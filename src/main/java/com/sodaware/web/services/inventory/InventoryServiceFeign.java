package com.sodaware.web.services.inventory;

import com.sodaware.web.services.inventory.model.SodaInventoryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
@Profile("local-discovery")
@RequiredArgsConstructor
public class InventoryServiceFeign implements SodaInventoryService {

    private final InventoryServiceFeignClient inventoryServiceFeignClient;

    @Override
    public Integer getOnHandInventory(UUID sodaId) {
        log.debug("Calling Inventory Service - SodaId : " + sodaId);
        ResponseEntity<List<SodaInventoryDto>> responseEntity = inventoryServiceFeignClient.getOnHandInventory(sodaId);
        Integer totalOnHand = Objects.requireNonNull(responseEntity.getBody()).stream().mapToInt(SodaInventoryDto::getQuantityOnHand).sum();
        log.debug("SodaId : " + sodaId + " On Hand is: " + totalOnHand);
        return totalOnHand;
    }
}
