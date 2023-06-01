package com.sodaware.web.services.filling;

import com.ms.soda.events.FillSodaEvent;
import com.ms.soda.events.NewInventoryEvent;
import com.sodaware.web.config.JMSConfig;
import com.sodaware.web.controller.NotFoundException;
import com.sodaware.web.domain.Soda;
import com.sodaware.web.model.SodaDto;
import com.sodaware.web.repository.SodaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class FillingSodaListener {

    private final SodaRepository sodaRepository;
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JMSConfig.FILLING_REQUEST_QUEUE)
    public void listen(FillSodaEvent sodaEvent){
        SodaDto sodaDto = sodaEvent.getSodaDto();
        Optional<Soda> sodaOptional = sodaRepository.findById(sodaDto.getId());
        if (sodaOptional.isEmpty()){
            throw new NotFoundException();
        }
        sodaDto.setQuantityOnHand(sodaOptional.get().getQuantityToWare());

        log.debug("Filled soda " + sodaOptional.get().getMinOnHand() + " : QOH: " + sodaDto.getQuantityOnHand());

        NewInventoryEvent newInventoryEvent = new NewInventoryEvent(sodaDto);
        jmsTemplate.convertAndSend(JMSConfig.NEW_INVENTORY_QUEUE, newInventoryEvent);
    }

}
