package com.micro.msscbreweryservice.web.services;

import com.micro.msscbreweryservice.web.controller.NotFoundException;
import com.micro.msscbreweryservice.web.domain.Beer;
import com.micro.msscbreweryservice.web.mappers.BeerMapper;
import com.micro.msscbreweryservice.web.model.BeerDto;
import com.micro.msscbreweryservice.web.repository.BeerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public BeerDto saveBeer(BeerDto beerDto) {
        return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(beerDto)));
    }

    @Override
    @Transactional(readOnly = true)
    public BeerDto getBeerById(UUID beerId) {
        return beerMapper.beerToBeerDto(beerRepository.findById(beerId).orElseThrow(NotFoundException::new));
    }

    @Override
    public BeerDto updateBeer(BeerDto beerDto) {
        Beer beer = beerRepository.findById(beerDto.getId()).orElseThrow(NotFoundException::new);
        beer.setBeerName(beerDto.getBeerName());
        beer.setBeerStyle(beerDto.getBeerStyle().name());
        beer.setPrice(beerDto.getPrice());
        beer.setUpc(beerDto.getUpc());
        return beerMapper.beerToBeerDto(beerRepository.save(beer));
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
