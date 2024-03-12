package com.food.ordering.system.domain.dto.track;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record TrackOrderRequest(@NotNull UUID orderTrackingId) {
    
}
