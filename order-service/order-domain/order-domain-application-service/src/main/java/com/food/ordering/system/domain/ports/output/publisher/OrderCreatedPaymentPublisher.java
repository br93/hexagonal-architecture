package com.food.ordering.system.domain.ports.output.publisher;

import com.food.ordering.system.domain.event.DomainEventPublisher;
import com.food.ordering.system.domain.event.OrderCreatedEvent;

public interface OrderCreatedPaymentPublisher extends DomainEventPublisher<OrderCreatedEvent>{
    
}
