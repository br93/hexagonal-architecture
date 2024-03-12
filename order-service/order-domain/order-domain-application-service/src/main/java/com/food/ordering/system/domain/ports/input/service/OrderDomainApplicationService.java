package com.food.ordering.system.domain.ports.input.service;

import com.food.ordering.system.domain.dto.create.CreateOrderRequest;
import com.food.ordering.system.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.domain.dto.track.TrackOrderRequest;
import com.food.ordering.system.domain.dto.track.TrackOrderResponse;

import jakarta.validation.Valid;

public interface OrderDomainApplicationService {

    CreateOrderResponse createOrder(@Valid CreateOrderRequest request);
    TrackOrderResponse trackOrder(@Valid TrackOrderRequest request);
    
}
