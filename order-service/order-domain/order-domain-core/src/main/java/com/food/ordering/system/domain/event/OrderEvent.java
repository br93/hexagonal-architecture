package com.food.ordering.system.domain.event;

import java.time.LocalDateTime;

import com.food.ordering.system.domain.entity.Order;

public abstract class OrderEvent implements DomainEvent<Order> {

    private Order order;
    private LocalDateTime createdAt;

    protected OrderEvent(Order order, LocalDateTime createdAt) {
        this.order = order;
        this.createdAt = createdAt;
    }

    public Order getOrder() {
        return order;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

}
