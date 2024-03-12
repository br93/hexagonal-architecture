package com.food.ordering.system.domain.ports.output.publisher;

import com.food.ordering.system.domain.event.DomainEventPublisher;
import com.food.ordering.system.domain.event.OrderPaidEvent;

public interface OrderPaidRestaurantPublisher extends DomainEventPublisher<OrderPaidEvent> {
    
}
