package com.food.ordering.system.valueobject;

import java.util.UUID;

import com.food.ordering.system.domain.valueobject.BaseId;

public class TrackingId extends BaseId<UUID>{

    public TrackingId(UUID value) {
        super(value);
    }
    
}
