package com.food.ordering.system.domain.dto.track;

import java.util.List;
import java.util.UUID;

import com.food.ordering.system.domain.valueobject.OrderStatus;

public record TrackOrderResponse(UUID orderTrackingId, OrderStatus orderStatus, List<String> failureMessages) {
    
}
