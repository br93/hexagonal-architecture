package com.food.ordering.system.domain.dto.create;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record CreateOrderRequest(@NotNull UUID customerId, @NotNull UUID restaurantId, @NotNull BigDecimal price,
        @NotNull List<OrderItemRequest> items, @NotNull OrderAddress orderAddress) {

}
