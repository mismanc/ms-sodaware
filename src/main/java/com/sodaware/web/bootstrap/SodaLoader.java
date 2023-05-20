package com.sodaware.web.bootstrap;

import com.sodaware.web.domain.Soda;
import com.sodaware.web.repository.SodaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SodaLoader implements CommandLineRunner {

    private final SodaRepository sodaRepository;

    public SodaLoader(SodaRepository sodaRepository) {
        this.sodaRepository = sodaRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadSodaObjects();
    }

    private void loadSodaObjects() {
        if (sodaRepository.count() == 0) {
            sodaRepository.save(Soda.builder()
                    .sodaName("Kızılay").sodaStyle("LEMON")
                    .quantityToBrew(200).minOnHand(11)
                    .upc(3370100000L)
                    .price(new BigDecimal("12.99"))
                    .build());

            sodaRepository.save(Soda.builder()
                    .sodaName("Beypazarı").sodaStyle("SIMPLE")
                    .quantityToBrew(200).minOnHand(12)
                    .upc(3370100001L)
                    .price(new BigDecimal("11.99"))
                    .build());
        }
    }
}
