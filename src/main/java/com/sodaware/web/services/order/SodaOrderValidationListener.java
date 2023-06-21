package com.sodaware.web.services.order;

import com.ms.soda.events.ValidateOrderRequest;
import com.ms.soda.events.ValidateOrderResult;
import com.sodaware.web.config.JMSConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SodaOrderValidationListener {

    private final SodaOrderValidator orderValidator;
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JMSConfig.VALIDATE_ORDER_QUEUE)
    public void listen(ValidateOrderRequest validateOrderRequest) {
        Boolean result = orderValidator.validateOrder(validateOrderRequest.getSodaOrderDto());
        jmsTemplate.convertAndSend(JMSConfig.VALIDATE_ORDER_RESPONSE_QUEUE, ValidateOrderResult.builder()
                .id(validateOrderRequest.getSodaOrderDto().getId()).isValid(result).build());
    }
}
