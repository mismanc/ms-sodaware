package com.sodaware.web.services.filling;

import com.ms.soda.events.FillSodaEvent;
import com.sodaware.web.config.JMSConfig;
import com.sodaware.web.domain.Soda;
import com.sodaware.web.mappers.SodaMapper;
import com.sodaware.web.repository.SodaRepository;
import com.sodaware.web.services.inventory.SodaInventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FillingService {

    private final SodaRepository sodaRepository;
    private final SodaInventoryService sodaInventoryService;
    private final JmsTemplate jmsTemplate;
    private final SodaMapper sodaMapper;

    @Scheduled(fixedRate = 7000)
    public void checkForLowInventory() {
        List<Soda> sodaList = sodaRepository.findAll();
        sodaList.forEach(soda -> {
            Integer invQOH = sodaInventoryService.getOnHandInventory(soda.getId());
            log.debug("Min On hand: " + soda.getMinOnHand());
            log.debug("Inventory On hand: " + soda.getMinOnHand());
            if (soda.getMinOnHand() >= invQOH) {
                jmsTemplate.convertAndSend(JMSConfig.FILLING_REQUEST_QUEUE, new FillSodaEvent(sodaMapper.sodaToSodaDto(soda)));
            }
        });
    }

}
