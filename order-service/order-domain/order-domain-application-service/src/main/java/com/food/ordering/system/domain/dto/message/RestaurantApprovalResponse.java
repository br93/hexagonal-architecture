package com.food.ordering.system.domain.dto.message;

import java.time.Instant;
import java.util.List;

import com.food.ordering.system.domain.valueobject.OrderApprovalStatus;

public record RestaurantApprovalResponse(String id, String sagaId, String orderId, String restaurantId,
        Instant createdAt, OrderApprovalStatus orderApprovalStatus, List<String> failureMessages) {

}
