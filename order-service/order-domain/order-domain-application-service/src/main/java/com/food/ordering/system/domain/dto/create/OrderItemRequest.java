package com.food.ordering.system.domain.dto.create;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record OrderItemRequest(@NotNull UUID productId, @NotNull Integer quantity, @NotNull BigDecimal price, @NotNull BigDecimal subtotal) {
    
}
