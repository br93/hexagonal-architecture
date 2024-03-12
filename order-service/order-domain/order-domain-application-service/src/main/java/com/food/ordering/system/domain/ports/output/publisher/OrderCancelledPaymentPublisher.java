package com.food.ordering.system.domain.ports.output.publisher;

import com.food.ordering.system.domain.event.DomainEventPublisher;
import com.food.ordering.system.domain.event.OrderCancelledEvent;

public interface OrderCancelledPaymentPublisher extends DomainEventPublisher<OrderCancelledEvent>{
    
}