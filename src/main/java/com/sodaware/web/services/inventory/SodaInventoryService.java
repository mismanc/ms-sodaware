package com.sodaware.web.services.inventory;

import java.util.UUID;

public interface SodaInventoryService {

    Integer getOnHandInventory(UUID sodaId);

}
