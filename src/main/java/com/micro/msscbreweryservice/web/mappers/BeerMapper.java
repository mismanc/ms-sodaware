package com.micro.msscbreweryservice.web.mappers;

import com.micro.msscbreweryservice.web.domain.Beer;
import com.micro.msscbreweryservice.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {

    BeerDto beerToBeerDto(Beer beer);

    Beer beerDtoToBeer(BeerDto beerDto);

}
