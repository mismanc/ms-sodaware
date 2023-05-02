package com.micro.msscbreweryservice.web.services;

import com.micro.msscbreweryservice.web.model.BeerDto;
import com.micro.msscbreweryservice.web.model.BeerStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {

    @Override
    public BeerDto saveBeer(BeerDto beerDto) {
        return BeerDto.builder().id(UUID.randomUUID())
                .beerName(beerDto.getBeerName())
                .beerStyle(beerDto.getBeerStyle())
                .upc(beerDto.getUpc())
                .build();
    }

    @Override
    public BeerDto getBeerById(UUID beerId) {
        return BeerDto.builder().id(UUID.randomUUID())
                .beerName("Galaxy Cat")
                .beerStyle(BeerStyle.ALE)
                .build();
    }

    @Override
    public void updateBeer(BeerDto beerDto) {

    }

    @Override
    public void deleteBeerById(UUID beerId) {
        log.debug("Deleting a beer... id= " + beerId.toString());
    }

    @Override
    public Page<BeerDto> getAllBears(Pageable pageable) {
        return null;
    }
}
