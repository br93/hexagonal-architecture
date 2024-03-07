package com.food.ordering.system.domain.event;

import java.time.LocalDateTime;

import com.food.ordering.system.domain.entity.Order;

public class OrderPaidEvent extends OrderEvent {

    public OrderPaidEvent(Order order, LocalDateTime createdAt) {
        super(order, createdAt);
    }
    
}
