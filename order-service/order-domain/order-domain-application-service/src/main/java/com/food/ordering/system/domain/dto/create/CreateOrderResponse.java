package com.food.ordering.system.domain.dto.create;

import java.util.UUID;

import com.food.ordering.system.domain.valueobject.OrderStatus;

public record CreateOrderResponse(UUID orderTrackingId, OrderStatus orderStatus, String message) {
    
}
