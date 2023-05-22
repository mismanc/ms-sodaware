package com.sodaware.web.mappers;

import com.sodaware.web.domain.Soda;
import com.sodaware.web.model.SodaDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
@DecoratedWith(SodaMapperDecorator.class)
public interface SodaMapper {

    SodaDto sodaToSodaDto(Soda soda);

    Soda sodaDtoToSoda(SodaDto sodaDto);

    SodaDto sodaToSodaDtoWithInventory(Soda soda);
}
