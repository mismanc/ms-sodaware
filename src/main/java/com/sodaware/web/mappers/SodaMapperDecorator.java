package com.sodaware.web.mappers;

import com.sodaware.web.domain.Soda;
import com.sodaware.web.model.SodaDto;
import com.sodaware.web.services.inventory.SodaInventoryService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class SodaMapperDecorator implements SodaMapper {

    private SodaInventoryService sodaInventoryService;
    private SodaMapper sodaMapper;

    @Autowired
    public void setSodaInventoryService(SodaInventoryService sodaInventoryService) {
        this.sodaInventoryService = sodaInventoryService;
    }

    @Autowired
    public void setSodaMapper(SodaMapper sodaMapper) {
        this.sodaMapper = sodaMapper;
    }

    @Override
    public Soda sodaDtoToSoda(SodaDto sodaDto) {
        return sodaMapper.sodaDtoToSoda(sodaDto);
    }

    @Override
    public SodaDto sodaToSodaDto(Soda soda) {
        SodaDto dto = sodaMapper.sodaToSodaDto(soda);
        dto.setQuantityOnHand(sodaInventoryService.getOnHandInventory(soda.getId()));
        System.out.println(dto);
        return dto;
    }
}
