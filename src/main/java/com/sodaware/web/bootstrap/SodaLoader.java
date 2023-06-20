package com.sodaware.web.bootstrap;

import com.ms.soda.model.SodaStyle;
import com.sodaware.web.domain.Soda;
import com.sodaware.web.repository.SodaRepository;
import org.springframework.boot.CommandLineRunner;

import java.math.BigDecimal;

// @Component
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
                    .sodaName("Kızılay").sodaStyle(SodaStyle.LEMON)
                    .quantityToWare(200).minOnHand(11)
                    .upc(3370100000L)
                    .price(new BigDecimal("12.99"))
                    .build());

            sodaRepository.save(Soda.builder()
                    .sodaName("Beypazarı").sodaStyle(SodaStyle.SIMPLE)
                    .quantityToWare(200).minOnHand(12)
                    .upc(3370100001L)
                    .price(new BigDecimal("11.99"))
                    .build());
        }
    }
}
