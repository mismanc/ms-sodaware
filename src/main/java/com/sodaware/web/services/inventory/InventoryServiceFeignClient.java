package com.sodaware.web.services.inventory;

import com.sodaware.web.config.FeignClientConfig;
import com.sodaware.web.services.inventory.model.SodaInventoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.UUID;

@Service
@FeignClient(name = "inventory-service", fallback = InventoryServiceFeignClientFailover.class, configuration = FeignClientConfig.class)
public interface InventoryServiceFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = SodaInventoryRestTemplateImpl.INVENTORY_PATH)
    ResponseEntity<List<SodaInventoryDto>> getOnHandInventory(@PathVariable UUID sodaId);

}
