package com.food.ordering.system.domain.ports.output.repository;

import java.util.Optional;

import com.food.ordering.system.domain.entity.Order;
import com.food.ordering.system.domain.valueobject.TrackingId;

public interface OrderRepository {
    
    Order save (Order order);
    Optional<Order> findByTrackingId(TrackingId trackingId);
}
