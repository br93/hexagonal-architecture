package com.food.ordering.system.domain;

import com.food.ordering.system.domain.dto.create.CreateOrderRequest;
import com.food.ordering.system.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.domain.dto.track.TrackOrderRequest;
import com.food.ordering.system.domain.dto.track.TrackOrderResponse;
import com.food.ordering.system.domain.ports.input.service.OrderDomainApplicationService;

import jakarta.validation.Valid;

class OrderDomainApplicationServiceImpl implements OrderDomainApplicationService{

    @Override
    public CreateOrderResponse createOrder(@Valid CreateOrderRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createOrder'");
    }

    @Override
    public TrackOrderResponse trackOrder(@Valid TrackOrderRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'trackOrder'");
    }
    
}
