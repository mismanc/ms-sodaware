package com.micro.msscbreweryservice.web.bootstrap;

import com.micro.msscbreweryservice.web.domain.Beer;
import com.micro.msscbreweryservice.web.repository.BeerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BeerLoader implements CommandLineRunner {

    private final BeerRepository beerRepository;

    public BeerLoader(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadBeerObjects();
    }

    private void loadBeerObjects() {
        if (beerRepository.count() == 0) {
            beerRepository.save(Beer.builder()
                    .beerName("Mango").beerStyle("IPA")
                    .quantityToBrew(200).minOnHand(11)
                    .upc(3370100000L)
                    .price(new BigDecimal("12.99"))
                    .build());

            beerRepository.save(Beer.builder()
                    .beerName("Galaxy").beerStyle("PALE_ALE")
                    .quantityToBrew(200).minOnHand(12)
                    .upc(3370100001L)
                    .price(new BigDecimal("11.99"))
                    .build());
        }
    }
}
