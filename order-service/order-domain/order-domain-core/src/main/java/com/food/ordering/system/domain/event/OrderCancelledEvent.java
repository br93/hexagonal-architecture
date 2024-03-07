package com.food.ordering.system.domain.event;

import java.time.LocalDateTime;

import com.food.ordering.system.domain.entity.Order;

public class OrderCancelledEvent extends OrderEvent{

    public OrderCancelledEvent(Order order, LocalDateTime createdAt) {
        super(order, createdAt);
    }
    
}
