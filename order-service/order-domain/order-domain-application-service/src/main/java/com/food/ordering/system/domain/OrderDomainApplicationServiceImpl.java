package com.food.ordering.system.domain;

import com.food.ordering.system.domain.dto.create.CreateOrderRequest;
import com.food.ordering.system.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.domain.dto.track.TrackOrderRequest;
import com.food.ordering.system.domain.dto.track.TrackOrderResponse;
import com.food.ordering.system.domain.ports.input.service.OrderDomainApplicationService;

import jakarta.validation.Valid;

class OrderDomainApplicationServiceImpl implements OrderDomainApplicationService{

    private final CreateOrderHandler createOrderHandler;
    private final TrackOrderHandler trackOrderHandler;

    

    public OrderDomainApplicationServiceImpl(CreateOrderHandler createOrderHandler,
            TrackOrderHandler trackOrderHandler) {
        this.createOrderHandler = createOrderHandler;
        this.trackOrderHandler = trackOrderHandler;
    }

    @Override
    public CreateOrderResponse createOrder(@Valid CreateOrderRequest request) {
       return this.createOrderHandler.createOrder(request);
    }

    @Override
    public TrackOrderResponse trackOrder(@Valid TrackOrderRequest request) {
        return this.trackOrderHandler.trackOrder(request);
    }
    
}
