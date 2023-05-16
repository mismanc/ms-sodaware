package com.micro.msscbreweryservice.web.services;


import com.micro.msscbreweryservice.web.model.BeerDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface BeerService {
    BeerDto saveBeer(BeerDto beerDto);

    BeerDto getBeerById(UUID beerId);

    BeerDto updateBeer(BeerDto beerDto);

    void deleteBeerById(UUID beerId);

    Page<BeerDto> getAllBears(Pageable pageable);
}
