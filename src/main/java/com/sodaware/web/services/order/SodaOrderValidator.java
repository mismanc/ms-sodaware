package com.sodaware.web.services.order;

import com.ms.soda.model.SodaOrderDto;
import com.sodaware.web.repository.SodaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
@RequiredArgsConstructor
@Slf4j
public class SodaOrderValidator {

    private final SodaRepository sodaRepository;

    public Boolean validateOrder(SodaOrderDto orderDto) {
        AtomicInteger sodasNotFound = new AtomicInteger();
        orderDto.getSodaOrderLines().forEach(line->{
            if (sodaRepository.findByUpc(Long.valueOf(line.getUpc())).isEmpty()){
                sodasNotFound.incrementAndGet();
            }
        });

        return sodasNotFound.get() == 0;
    }
}
