package com.food.ordering.system.domain.dto.create;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;

public record OrderAddress(@NotNull String street, @NotNull @Max(value = 10) String postalCode, @NotNull String city) {
    
}
