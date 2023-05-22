package com.sodaware;

import com.sodaware.web.services.inventory.SodaInventoryService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@Disabled
@SpringBootTest
class MsSodawareServiceApplicationTests {

    public static final UUID SODA_1_UUID = UUID.fromString("0a818933-087d-47f2-ad83-2f986ed087eb");
    public static final UUID SODA_2_UUID = UUID.fromString("a712d914-61ea-4623-8bd0-32c0f6545bfd");
    public static final UUID SODA_3_UUID = UUID.fromString("026cc3c8-3a0c-4083-a05b-e908048c1b08");


    @Autowired
    SodaInventoryService sodaInventoryService;

    @Test
    void contextLoads() {
    }

    @Test
    public void getOnHandInventory(){
        Integer qoh = sodaInventoryService.getOnHandInventory(SODA_1_UUID);
        System.out.println(qoh);
    }

}
